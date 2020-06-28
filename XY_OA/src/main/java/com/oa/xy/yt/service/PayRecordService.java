package com.oa.xy.yt.service;

import com.oa.xy.yt.entiy.PayRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Date;
import java.util.List;

public interface PayRecordService {
  PayRecord save(PayRecord payRecord);

  void deleteById(String payId);

  Page<PayRecord> findAll(Pageable pageable);

  Page<PayRecord> findAll(Specification specification, Pageable pageable);

  List<PayRecord> findAll(Sort sort);

  List<PayRecord> findAll(Specification specification, Sort sort);

  PayRecord findByEmpCodeAndPayDate(String empCode, Date payDate);
}
