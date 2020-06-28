package com.oa.xy.yt.controller;

import com.oa.xy.base.entiy.SysUser;
import com.oa.xy.pojo.EzuiGridData;
import com.oa.xy.yt.entiy.PayRecord;
import com.oa.xy.yt.service.PayRecordService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("PayRecordControl")
public class PayRecordController {
  Sort.Direction DIRECTION = Sort.DEFAULT_DIRECTION;
  @Autowired PayRecordService payRecordService;

  @RequestMapping(value = "/payRecord.htm")
  public String payRecord(Model model) {
    return "yt/payRecord";
  }

  @RequiresPermissions("payRecord:find")
  @RequestMapping(value = "/find", method = RequestMethod.POST)
  @ResponseBody
  public EzuiGridData find(
      Integer page, Integer rows, String sort, String order, String payDate, String empCode) {
    EzuiGridData ezuiGridData = new EzuiGridData();
    if (("desc").equals(order)) {
      DIRECTION = Sort.Direction.DESC;
    } else {
      DIRECTION = Sort.Direction.ASC;
    }
    Specification specification =
        new Specification<PayRecord>() {
          @Override
          public Predicate toPredicate(
              Root<PayRecord> root,
              CriteriaQuery<?> criteriaQuery,
              CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            if (!StringUtils.isEmpty(empCode)) {
              predicates.add(criteriaBuilder.equal(root.get("empCode"), empCode));
            }
            if (!StringUtils.isEmpty(payDate)) {
              try {
                predicates.add(
                    criteriaBuilder.equal(root.get("payDate").as(Date.class), sdf.parse(payDate)));
              } catch (ParseException e) {
                e.printStackTrace();
              }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
          }
        };
    if (StringUtils.isEmpty(page) && StringUtils.isEmpty(rows)) {
      List<PayRecord> payRecords =
          payRecordService.findAll(specification, new Sort(DIRECTION, "rpDate"));
      ezuiGridData.setRows(payRecords);
    } else {
      Pageable pageable = new PageRequest(page - 1, rows, DIRECTION, sort);
      Page<PayRecord> payRecords = payRecordService.findAll(specification, pageable);
      ezuiGridData.setRows(payRecords.getContent());
      ezuiGridData.setTotal(payRecords.getTotalElements());
    }
    return ezuiGridData;
  }

  @RequiresPermissions("payRecord:save")
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public PayRecord save(PayRecord payRecord) {
    SysUser sUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
    Date date = new Date();
    payRecord.setCreator(sUser.getUserCode());
    payRecord.setCreatedTim(new Timestamp(date.getTime()));
    payRecord.setChanger(sUser.getUserCode());
    payRecord.setChangedTim(new Timestamp(date.getTime()));
    return payRecordService.save(payRecord);
  }

  @RequiresPermissions("payRecord:update")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public PayRecord update(PayRecord payRecord) {
    SysUser sUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
    Date date = new Date();
    payRecord.setChanger(sUser.getUserCode());
    payRecord.setChangedTim(new Timestamp(date.getTime()));
    return payRecordService.save(payRecord);
  }

  @RequiresPermissions("payRecord:delete")
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public void delete(String id) {
    payRecordService.deleteById(id);
  }
}
