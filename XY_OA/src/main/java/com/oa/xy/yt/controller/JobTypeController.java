package com.oa.xy.yt.controller;

import com.oa.xy.base.entiy.SysUser;
import com.oa.xy.pojo.EzuiGridData;
import com.oa.xy.yt.entiy.JobType;
import com.oa.xy.yt.service.JobTypeService;
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
@RequestMapping("JobTypeControl")
public class JobTypeController {
  Sort.Direction DIRECTION = Sort.DEFAULT_DIRECTION;
  @Autowired JobTypeService jobTypeService;

  @RequestMapping(value = "/jobType.htm")
  public String jobType(Model model) {
    return "yt/jobType";
  }

  @RequiresPermissions("jobType:find")
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
        new Specification<JobType>() {
          @Override
          public Predicate toPredicate(
              Root<JobType> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
          }
        };
    if (StringUtils.isEmpty(page) && StringUtils.isEmpty(rows)) {
      List<JobType> jobTypes =
          jobTypeService.findAll(specification, new Sort(DIRECTION, "empCode"));
      ezuiGridData.setRows(jobTypes);
    } else {
      Pageable pageable = new PageRequest(page - 1, rows, DIRECTION, sort);
      Page<JobType> jobTypes = jobTypeService.findAll(specification, pageable);
      ezuiGridData.setRows(jobTypes.getContent());
      ezuiGridData.setTotal(jobTypes.getTotalElements());
    }
    return ezuiGridData;
  }

  @RequiresPermissions("jobType:save")
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public JobType save(JobType jobType) {
    SysUser sUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
    Date date = new Date();
    jobType.setCreator(sUser.getUserCode());
    jobType.setCreatedTim(new Timestamp(date.getTime()));
    jobType.setChanger(sUser.getUserCode());
    jobType.setChangedTim(new Timestamp(date.getTime()));
    return jobTypeService.save(jobType);
  }

  @RequiresPermissions("jobType:update")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public JobType update(JobType jobType) {
    SysUser sUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
    Date date = new Date();
    jobType.setChanger(sUser.getUserCode());
    jobType.setChangedTim(new Timestamp(date.getTime()));
    return jobTypeService.save(jobType);
  }

  @RequiresPermissions("jobType:delete")
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public void delete(String id) {
    jobTypeService.deleteById(id);
  }

  @RequestMapping(value = "/comboGrid", method = RequestMethod.POST)
  @ResponseBody
  public EzuiGridData comboGrid(String q, int page, int rows) {
    EzuiGridData ezuiDatagridData = new EzuiGridData();
    List<Map<String, Object>> results = new ArrayList<>();
    Pageable pageable = new PageRequest(page - 1, rows, Sort.Direction.ASC, "jobCode");
    Specification specification =
        new Specification<JobType>() {
          @Override
          public Predicate toPredicate(
              Root<JobType> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> list = new ArrayList<Predicate>();
            if (!StringUtils.isEmpty(q)) {
              List<Predicate> list1 = new ArrayList<Predicate>();
              list1.add(criteriaBuilder.like(root.get("jobCode").as(String.class), q + "%"));
              list1.add(criteriaBuilder.like(root.get("jobName").as(String.class), q + "%"));
              list.add(criteriaBuilder.or(list1.toArray(new Predicate[list.size()])));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
          }
        };
    Page<JobType> jobTypes = jobTypeService.findAll(specification, pageable);
    ezuiDatagridData.setRows(jobTypes.getContent());
    ezuiDatagridData.setTotal(jobTypes.getTotalElements());
    return ezuiDatagridData;
  }
}
