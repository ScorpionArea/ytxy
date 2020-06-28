package com.oa.xy.yt.repository;

import com.oa.xy.yt.entiy.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PerformanceRepository
    extends JpaRepository<Performance, String>, JpaSpecificationExecutor<Performance> {}
