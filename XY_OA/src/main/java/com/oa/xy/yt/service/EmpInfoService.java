package com.oa.xy.yt.service;

import com.oa.xy.yt.entiy.EmpInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface EmpInfoService {

  EmpInfo save(EmpInfo empInfo);

  void deleteById(String empId);

  Page<EmpInfo> findAll(Pageable pageable);

  Page<EmpInfo> findAll(Specification specification, Pageable pageable);

  List<EmpInfo> findAll(Sort sort);

  List<EmpInfo> findAll(Specification specification, Sort sort);

  EmpInfo findByEmpCode(String empCode);
}
