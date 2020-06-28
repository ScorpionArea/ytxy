package com.oa.xy.yt.repository;

import com.oa.xy.yt.entiy.RewardsPunishments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface RewardsPunishmentsRepository
    extends JpaRepository<RewardsPunishments, String>,
        JpaSpecificationExecutor<RewardsPunishments> {
  @Query(
      value =
          "SELECT SUM(RP_NUM) FROM rewards_punishments WHERE DATE_FORMAT(RP_DATE, '%Y-%m') = DATE_FORMAT(:rpDate, '%Y-%m') AND EMP_CODE =:empCode",
      nativeQuery = true)
  Float sumByRpDateAndEmpCode(Date rpDate, String empCode);
}
