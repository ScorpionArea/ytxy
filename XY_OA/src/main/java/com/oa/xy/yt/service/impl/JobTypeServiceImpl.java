package com.oa.xy.yt.service.impl;

import com.oa.xy.yt.entiy.JobType;
import com.oa.xy.yt.repository.JobTypeRepository;
import com.oa.xy.yt.service.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobTypeServiceImpl implements JobTypeService {
  @Autowired JobTypeRepository jobTypeRepository;

  @Override
  public JobType save(JobType jobType) {
    return jobTypeRepository.save(jobType);
  }

  @Override
  public void deleteById(String empId) {
    jobTypeRepository.deleteById(empId);
  }

  @Override
  public Page<JobType> findAll(Pageable pageable) {
    return jobTypeRepository.findAll(pageable);
  }

  @Override
  public Page<JobType> findAll(Specification specification, Pageable pageable) {
    return jobTypeRepository.findAll(specification, pageable);
  }

  @Override
  public List<JobType> findAll(Sort sort) {
    return jobTypeRepository.findAll(sort);
  }

  @Override
  public List<JobType> findAll(Specification specification, Sort sort) {
    return jobTypeRepository.findAll(specification, sort);
  }

  @Override
  public JobType findByJobCode(String jobCode) {
    return jobTypeRepository.findByJobCode(jobCode);
  }
}
