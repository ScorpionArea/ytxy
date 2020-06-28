package com.oa.xy.yt.service.impl;

import com.oa.xy.yt.entiy.EmpInfo;
import com.oa.xy.yt.repository.EmpInfoRepository;
import com.oa.xy.yt.service.EmpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpInfoServiceImpl implements EmpInfoService {
  @Autowired EmpInfoRepository empInfoRepository;

  @Override
  public EmpInfo save(EmpInfo empInfo) {
    return empInfoRepository.save(empInfo);
  }

  @Override
  public void deleteById(String empId) {
    empInfoRepository.deleteById(empId);
  }

  @Override
  public Page<EmpInfo> findAll(Pageable pageable) {
    return empInfoRepository.findAll(pageable);
  }

  @Override
  public Page<EmpInfo> findAll(Specification specification, Pageable pageable) {
    return empInfoRepository.findAll(specification, pageable);
  }

  @Override
  public List<EmpInfo> findAll(Sort sort) {
    return empInfoRepository.findAll(sort);
  }

  @Override
  public List<EmpInfo> findAll(Specification specification, Sort sort) {
    return empInfoRepository.findAll(specification, sort);
  }

  @Override
  public EmpInfo findByEmpCode(String empCode) {
    return empInfoRepository.findByEmpCode(empCode);
  }
}
