package com.oa.xy.yt.service.impl;

import com.oa.xy.yt.entiy.RewardsPunishments;
import com.oa.xy.yt.repository.RewardsPunishmentsRepository;
import com.oa.xy.yt.service.RewardsPunishmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class RewardsPunishmentsServiceImpl implements RewardsPunishmentsService {
  @Autowired RewardsPunishmentsRepository rewardsPunishmentsRepository;

  @Override
  public RewardsPunishments save(RewardsPunishments rewardsPunishments) {
    return rewardsPunishmentsRepository.save(rewardsPunishments);
  }

  @Override
  public void deleteById(String rpId) {
    rewardsPunishmentsRepository.deleteById(rpId);
  }

  @Override
  public Page<RewardsPunishments> findAll(Pageable pageable) {
    return rewardsPunishmentsRepository.findAll(pageable);
  }

  @Override
  public Page<RewardsPunishments> findAll(Specification specification, Pageable pageable) {
    return rewardsPunishmentsRepository.findAll(specification, pageable);
  }

  @Override
  public List<RewardsPunishments> findAll(Sort sort) {
    return rewardsPunishmentsRepository.findAll(sort);
  }

  @Override
  public List<RewardsPunishments> findAll(Specification specification, Sort sort) {
    return rewardsPunishmentsRepository.findAll(specification, sort);
  }

  @Override
  public Float sumByRpDateAndEmpCode(Date rpDate, String empCode) {
    return rewardsPunishmentsRepository.sumByRpDateAndEmpCode(rpDate, empCode);
  }
}
