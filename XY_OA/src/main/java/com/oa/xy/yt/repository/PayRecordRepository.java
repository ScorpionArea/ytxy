package com.oa.xy.yt.repository;

import com.oa.xy.yt.entiy.PayRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.sql.Date;

public interface PayRecordRepository
    extends JpaRepository<PayRecord, String>, JpaSpecificationExecutor<PayRecord> {
  PayRecord findByEmpCodeAndPayDate(String empCode, Date payDate);
}
