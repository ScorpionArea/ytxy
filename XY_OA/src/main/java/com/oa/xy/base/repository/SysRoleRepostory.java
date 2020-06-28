package com.oa.xy.base.repository;

import com.oa.xy.base.entiy.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysRoleRepostory
    extends JpaRepository<SysRole, String>, JpaSpecificationExecutor<SysRole> {
  SysRole findByRoleCode(String roleCode);
}
