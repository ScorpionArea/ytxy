package com.oa.xy.yt.controller;

import com.oa.xy.pojo.EzuiGridData;
import com.oa.xy.yt.entiy.EmpInfo;
import com.oa.xy.yt.entiy.JobType;
import com.oa.xy.yt.service.EmpInfoService;
import com.oa.xy.yt.service.JobTypeService;
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
@RequestMapping("EmpJobControl")
public class EmpJobController {
  Sort.Direction DIRECTION = Sort.DEFAULT_DIRECTION;
  @Autowired EmpInfoService empInfoService;
  @Autowired JobTypeService jobTypeService;

  @RequiresPermissions("empJob:save")
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public EmpInfo save(@RequestBody EmpInfo empInfo) {
    return empInfoService.save(empInfo);
  }

  @RequiresPermissions("empJob:find")
  @RequestMapping(value = "/find", method = RequestMethod.POST)
  @ResponseBody
  public EzuiGridData find(Integer page, Integer rows, String sort, String order, String empCode) {
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
          jobTypeService.findAll(specification, new Sort(DIRECTION, "roleCode"));
      ezuiGridData.setRows(jobTypes);
      ezuiGridData.setTotal(jobTypes.size());
    } else {
      Pageable pageable = new PageRequest(page - 1, rows, DIRECTION, sort);
      Page<JobType> jobTypes = jobTypeService.findAll(specification, pageable);
      if (empCode != null) {
        for (JobType jobType : empInfoService.findByEmpCode(empCode).getJobTypes()) {
          for (JobType jobType1 : jobTypes.getContent()) {
            if (jobType.getJobId().equals(jobType1.getJobId())) {
              jobType1.setCk(true);
            }
          }
        }
      }
      ezuiGridData.setRows(jobTypes.getContent());
      ezuiGridData.setTotal(jobTypes.getTotalElements());
    }
    return ezuiGridData;
  }
}
