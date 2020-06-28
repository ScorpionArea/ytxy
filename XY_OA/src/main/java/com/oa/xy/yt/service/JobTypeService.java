package com.oa.xy.yt.service;

import com.oa.xy.yt.entiy.JobType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface JobTypeService {
  JobType save(JobType jobType);

  void deleteById(String empId);

  Page<JobType> findAll(Pageable pageable);

  Page<JobType> findAll(Specification specification, Pageable pageable);

  List<JobType> findAll(Sort sort);

  List<JobType> findAll(Specification specification, Sort sort);

  JobType findByJobCode(String jobCode);
}
