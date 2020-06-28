package com.oa.xy.yt.service;

import com.oa.xy.yt.entiy.Performance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface PerformanceService {
  Performance save(Performance performance);

  void deleteById(String perfId);

  Page<Performance> findAll(Pageable pageable);

  Page<Performance> findAll(Specification specification, Pageable pageable);

  List<Performance> findAll(Sort sort);

  List<Performance> findAll(Specification specification, Sort sort);
}
