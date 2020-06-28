package com.oa.xy.yt.controller;

import com.oa.xy.base.entiy.SysUser;
import com.oa.xy.pojo.EzuiGridData;
import com.oa.xy.yt.entiy.EmpInfo;
import com.oa.xy.yt.service.EmpInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("EmpInfoControl")
public class EmpInfoController {
  Sort.Direction DIRECTION = Sort.DEFAULT_DIRECTION;
  @Autowired EmpInfoService empInfoService;

  @RequestMapping(value = "/empInfo.htm")
  public String empInfo(Model model) {
    return "yt/empInfo";
  }

  @RequiresPermissions("empInfo:find")
  @RequestMapping(value = "/find", method = RequestMethod.POST)
  @ResponseBody
  public EzuiGridData find(Integer page, Integer rows, String sort, String order) {
    EzuiGridData ezuiGridData = new EzuiGridData();
    if (("desc").equals(order)) {
      DIRECTION = Sort.Direction.DESC;
    } else {
      DIRECTION = Sort.Direction.ASC;
    }
    Specification specification =
        new Specification<EmpInfo>() {
          @Override
          public Predicate toPredicate(
              Root<EmpInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            //            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //            if (!StringUtils.isEmpty(userState)) {
            //              predicates.add(criteriaBuilder.equal(root.get("userState"), userState));
            //            }
            //            if (!StringUtils.isEmpty(endDate)) {
            //              try {
            //                predicates.add(
            //                    criteriaBuilder.lessThanOrEqualTo(
            //                        root.get("tideDate").as(Date.class), sdf.parse(endDate)));
            //              } catch (ParseException e) {
            //                e.printStackTrace();
            //              }
            //            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
          }
        };
    if (StringUtils.isEmpty(page) && StringUtils.isEmpty(rows)) {
      List<EmpInfo> empInfos =
          empInfoService.findAll(specification, new Sort(DIRECTION, "empCode"));
      ezuiGridData.setRows(empInfos);
    } else {
      Pageable pageable = new PageRequest(page - 1, rows, DIRECTION, sort);
      Page<EmpInfo> empInfos = empInfoService.findAll(specification, pageable);
      ezuiGridData.setRows(empInfos.getContent());
      ezuiGridData.setTotal(empInfos.getTotalElements());
    }
    return ezuiGridData;
  }

  @RequiresPermissions("empInfo:save")
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public EmpInfo save(EmpInfo empInfo) {
    SysUser sUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
    Date date = new Date();
    empInfo.setCreator(sUser.getUserCode());
    empInfo.setCreatedTim(new Timestamp(date.getTime()));
    empInfo.setChanger(sUser.getUserCode());
    empInfo.setChangedTim(new Timestamp(date.getTime()));
    return empInfoService.save(empInfo);
  }

  @RequiresPermissions("empInfo:update")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public EmpInfo update(EmpInfo empInfo) {
    SysUser sUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
    Date date = new Date();
    empInfo.setChanger(sUser.getUserCode());
    empInfo.setChangedTim(new Timestamp(date.getTime()));
    return empInfoService.save(empInfo);
  }

  @RequiresPermissions("empInfo:delete")
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public void delete(String id) {
    empInfoService.deleteById(id);
  }

  @RequestMapping(value = "/comboGrid", method = RequestMethod.POST)
  @ResponseBody
  public EzuiGridData comboGrid(String q, int page, int rows) {
    EzuiGridData ezuiDatagridData = new EzuiGridData();
    List<Map<String, Object>> results = new ArrayList<>();
    Pageable pageable = new PageRequest(page - 1, rows, Sort.Direction.ASC, "empCode");
    Specification specification =
        new Specification<EmpInfo>() {
          @Override
          public Predicate toPredicate(
              Root<EmpInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> list = new ArrayList<Predicate>();
            if (!StringUtils.isEmpty(q)) {
              List<Predicate> list1 = new ArrayList<Predicate>();
              list1.add(criteriaBuilder.like(root.get("empCode").as(String.class), q + "%"));
              list1.add(criteriaBuilder.like(root.get("empName").as(String.class), q + "%"));
              list.add(criteriaBuilder.or(list1.toArray(new Predicate[list.size()])));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
          }
        };
    Page<EmpInfo> empInfos = empInfoService.findAll(specification, pageable);
    ezuiDatagridData.setRows(empInfos.getContent());
    ezuiDatagridData.setTotal(empInfos.getTotalElements());
    return ezuiDatagridData;
  }

  @RequestMapping(value = "/comboBox", method = RequestMethod.POST)
  @ResponseBody
  public List<EmpInfo> comboBox(String q) {
    Specification specification =
        new Specification<EmpInfo>() {
          @Override
          public Predicate toPredicate(
              Root<EmpInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> list = new ArrayList<Predicate>();
            if (!StringUtils.isEmpty(q)) {
              List<Predicate> list1 = new ArrayList<Predicate>();
              list1.add(criteriaBuilder.like(root.get("empCode").as(String.class), q + "%"));
              list1.add(criteriaBuilder.like(root.get("empName").as(String.class), q + "%"));
              list.add(criteriaBuilder.or(list1.toArray(new Predicate[list.size()])));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
          }
        };
    List<EmpInfo> empInfos = empInfoService.findAll(specification, new Sort(DIRECTION, "empCode"));
    return empInfos;
  }
}
