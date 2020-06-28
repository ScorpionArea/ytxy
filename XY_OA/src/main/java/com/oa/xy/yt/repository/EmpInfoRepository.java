package com.oa.xy.yt.repository;

import com.oa.xy.yt.entiy.EmpInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmpInfoRepository
    extends JpaRepository<EmpInfo, String>, JpaSpecificationExecutor<EmpInfo> {
  EmpInfo findByEmpCode(String empCode);
}
