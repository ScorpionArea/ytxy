package com.oa.xy.base.repository;

import com.oa.xy.base.entiy.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysUserRepostory
    extends JpaRepository<SysUser, String>, JpaSpecificationExecutor<SysUser> {
  SysUser findByUserCode(String userCode);
}
