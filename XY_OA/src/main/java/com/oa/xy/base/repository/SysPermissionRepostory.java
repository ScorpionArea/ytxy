package com.oa.xy.base.repository;

import com.oa.xy.base.entiy.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Chen's
 * @date 2019/8/2 16:28
 */
public interface SysPermissionRepostory
    extends JpaRepository<SysPermission, String>, JpaSpecificationExecutor<SysPermission> {
  List<SysPermission> findByParentIdOrderBySortedAsc(
          String parentId);

  List<SysPermission> findByParentIdAndPermissionTypeOrderBySortedAsc(
          String parentId, String permissionType);

  List<SysPermission> findByParentIdAndPermissionStateOrderBySortedAsc(
          String parentId, boolean permissionState);
}
