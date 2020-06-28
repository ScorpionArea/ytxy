package com.oa.xy.yt.controller;

import com.oa.xy.base.entiy.SysUser;
import com.oa.xy.pojo.EzuiGridData;
import com.oa.xy.yt.entiy.Performance;
import com.oa.xy.yt.service.PerformanceService;
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

@Controller
@RequestMapping("PerformanceControl")
public class PerformanceController {
  Sort.Direction DIRECTION = Sort.DEFAULT_DIRECTION;
  @Autowired PerformanceService performanceService;

  @RequestMapping(value = "/performance.htm")
  public String performance(Model model) {
    return "yt/performance";
  }

  @RequiresPermissions("perf:find")
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
        new Specification<Performance>() {
          @Override
          public Predicate toPredicate(
              Root<Performance> root,
              CriteriaQuery<?> criteriaQuery,
              CriteriaBuilder criteriaBuilder) {
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
      List<Performance> performances =
          performanceService.findAll(specification, new Sort(DIRECTION, "empCode"));
      ezuiGridData.setRows(performances);
    } else {
      Pageable pageable = new PageRequest(page - 1, rows, DIRECTION, sort);
      Page<Performance> performances = performanceService.findAll(specification, pageable);
      ezuiGridData.setRows(performances.getContent());
      ezuiGridData.setTotal(performances.getTotalElements());
    }
    return ezuiGridData;
  }

  @RequiresPermissions("perf:save")
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public Performance save(Performance performance) {
    SysUser sUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
    Date date = new Date();
    performance.setCreator(sUser.getUserCode());
    performance.setCreatedTim(new Timestamp(date.getTime()));
    performance.setChanger(sUser.getUserCode());
    performance.setChangedTim(new Timestamp(date.getTime()));
    return performanceService.save(performance);
  }

  @RequiresPermissions("perf:update")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public Performance update(Performance performance) {
    SysUser sUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
    Date date = new Date();
    performance.setChanger(sUser.getUserCode());
    performance.setChangedTim(new Timestamp(date.getTime()));
    return performanceService.save(performance);
  }

  @RequiresPermissions("perf:delete")
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public void delete(String id) {
    performanceService.deleteById(id);
  }
}
