package com.oa.xy.base.controller;

import com.oa.xy.base.entiy.SysPermission;
import com.oa.xy.base.entiy.SysRole;
import com.oa.xy.base.service.SysPermissionService;
import com.oa.xy.base.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("RolePermissionControl")
public class RolePermissionController {
  @Autowired SysRoleService sysRoleService;
  @Autowired SysPermissionService sysPermissionService;

  @RequestMapping(value = "/rolePermission.htm")
  public String rolePermission(Model model) {
    return "base/rolePermission";
  }

  @RequiresPermissions("rolePermission:save")
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public SysRole save(@RequestBody SysRole sysRole) {
    return sysRoleService.save(sysRole);
  }

  @RequiresPermissions("rolePermission:find")
  @RequestMapping(value = "/find", method = RequestMethod.POST)
  @ResponseBody
  public List<SysPermission> findAllTreeBypermissionState(String roleCode) {
    List<SysPermission> sysPermissions =
        findByParentIdAndPermissionStateOrderBySortedAsc("0", true);
    if (roleCode != null) {
      for (SysPermission sysPermission :
          sysRoleService.findByRoleCode(roleCode).getSysPermissions()) {
        isCheck(sysPermissions, sysPermission);
      }
    }
    return sysPermissions;
  }
  // 判断当前角色哪些被选中
  public void isCheck(List<SysPermission> sysPermissions, SysPermission sysPermission) {
    for (SysPermission sysPermission1 : sysPermissions) {
      if (sysPermission.getId().equals(sysPermission1.getId())) {
        sysPermission1.setChecked(true);
        break;
      } else if (sysPermission1.getChildren() != null) {
        isCheck(sysPermission1.getChildren(), sysPermission);
      }
    }
  }

  // 树
  public List<SysPermission> findByParentIdAndPermissionStateOrderBySortedAsc(
      String parentId, boolean permissionState) {
    List<SysPermission> sysPermissions =
        sysPermissionService.findByParentIdAndPermissionStateOrderBySortedAsc(
            parentId, permissionState);
    for (SysPermission sysPermission : sysPermissions) {
      if (sysPermissionService
          .findByParentIdAndPermissionStateOrderBySortedAsc(sysPermission.getId(), permissionState)
          .isEmpty()) {
        sysPermission.setState("");
      } else {
        sysPermission.setChildren(
            findByParentIdAndPermissionStateOrderBySortedAsc(
                sysPermission.getId(), permissionState));
      }
    }
    return sysPermissions;
  }
}
