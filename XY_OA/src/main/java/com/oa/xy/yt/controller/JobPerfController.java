package com.oa.xy.yt.controller;

import com.oa.xy.pojo.EzuiGridData;
import com.oa.xy.yt.entiy.JobType;
import com.oa.xy.yt.entiy.Performance;
import com.oa.xy.yt.service.JobTypeService;
import com.oa.xy.yt.service.PerformanceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("JobPerfControl")
public class JobPerfController {
  Sort.Direction DIRECTION = Sort.DEFAULT_DIRECTION;
  @Autowired JobTypeService jobTypeService;
  @Autowired PerformanceService performanceService;

  @RequiresPermissions("jobPerf:save")
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public JobType save(@RequestBody JobType jobType) {
    return jobTypeService.save(jobType);
  }

  @RequiresPermissions("jobPerf:find")
  @RequestMapping(value = "/find", method = RequestMethod.POST)
  @ResponseBody
  public EzuiGridData find(Integer page, Integer rows, String sort, String order, String jobCode) {
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
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
          }
        };
    if (StringUtils.isEmpty(page) && StringUtils.isEmpty(rows)) {
      List<Performance> performances =
          performanceService.findAll(specification, new Sort(DIRECTION, "roleCode"));
      ezuiGridData.setRows(performances);
      ezuiGridData.setTotal(performances.size());
    } else {
      Pageable pageable = new PageRequest(page - 1, rows, DIRECTION, sort);
      Page<Performance> performances = performanceService.findAll(specification, pageable);
      if (jobCode != null) {
        for (Performance performance : jobTypeService.findByJobCode(jobCode).getPerformances()) {
          for (Performance performance1 : performances.getContent()) {
            if (performance.getPerfId().equals(performance1.getPerfId())) {
              performance1.setCk(true);
            }
          }
        }
      }
      ezuiGridData.setRows(performances.getContent());
      ezuiGridData.setTotal(performances.getTotalElements());
    }
    return ezuiGridData;
  }
}
