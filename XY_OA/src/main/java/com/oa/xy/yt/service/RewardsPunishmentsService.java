package com.oa.xy.yt.service;

import com.oa.xy.yt.entiy.RewardsPunishments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
import java.util.List;

public interface RewardsPunishmentsService {
  RewardsPunishments save(RewardsPunishments rewardsPunishments);

  void deleteById(String rpId);

  Page<RewardsPunishments> findAll(Pageable pageable);

  Page<RewardsPunishments> findAll(Specification specification, Pageable pageable);

  List<RewardsPunishments> findAll(Sort sort);

  List<RewardsPunishments> findAll(Specification specification, Sort sort);

  Float sumByRpDateAndEmpCode(Date rpDate, String empCode);
}
