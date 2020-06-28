package com.oa.xy.yt.service.impl;

import com.oa.xy.yt.entiy.Performance;
import com.oa.xy.yt.repository.PerformanceRepository;
import com.oa.xy.yt.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PerformanceServiceImpl implements PerformanceService {
  @Autowired PerformanceRepository performanceRepository;

  @Override
  public Performance save(Performance performance) {
    return performanceRepository.save(performance);
  }

  @Override
  public void deleteById(String perfId) {
    performanceRepository.deleteById(perfId);
  }

  @Override
  public Page<Performance> findAll(Pageable pageable) {
    return performanceRepository.findAll(pageable);
  }

  @Override
  public Page<Performance> findAll(Specification specification, Pageable pageable) {
    return performanceRepository.findAll(specification, pageable);
  }

  @Override
  public List<Performance> findAll(Sort sort) {
    return performanceRepository.findAll(sort);
  }

  @Override
  public List<Performance> findAll(Specification specification, Sort sort) {
    return performanceRepository.findAll(specification, sort);
  }
}
