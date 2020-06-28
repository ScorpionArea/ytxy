package com.oa.xy.yt.repository;

import com.oa.xy.yt.entiy.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JobTypeRepository
    extends JpaRepository<JobType, String>, JpaSpecificationExecutor<JobType> {
  JobType findByJobCode(String jobCode);
}
