package com.oa.xy.yt.service.impl;

import com.oa.xy.yt.entiy.PayRecord;
import com.oa.xy.yt.repository.PayRecordRepository;
import com.oa.xy.yt.service.PayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class PayRecordServiceImpl implements PayRecordService {
  @Autowired PayRecordRepository payRecordRepository;

  @Override
  public PayRecord save(PayRecord payRecord) {
    return payRecordRepository.save(payRecord);
  }

  @Override
  public void deleteById(String payId) {
    payRecordRepository.deleteById(payId);
  }

  @Override
  public Page<PayRecord> findAll(Pageable pageable) {
    return payRecordRepository.findAll(pageable);
  }

  @Override
  public Page<PayRecord> findAll(Specification specification, Pageable pageable) {
    return payRecordRepository.findAll(specification, pageable);
  }

  @Override
  public List<PayRecord> findAll(Sort sort) {
    return payRecordRepository.findAll(sort);
  }

  @Override
  public List<PayRecord> findAll(Specification specification, Sort sort) {
    return payRecordRepository.findAll(specification, sort);
  }

  @Override
  public PayRecord findByEmpCodeAndPayDate(String empCode, Date payDate) {
    return payRecordRepository.findByEmpCodeAndPayDate(empCode, payDate);
  }
}
