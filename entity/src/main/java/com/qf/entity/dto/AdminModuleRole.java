package com.qf.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qf.entity.base.BaseDto;

@TableName("admin_module_role")
public class AdminModuleRole extends BaseDto {
  @TableField("admin_module_id")
  private Long adminModuleId;
  private Long adminRoleId;

  public Long getAdminModuleId() {
    return adminModuleId;
  }

  public void setAdminModuleId(Long adminModuleId) {
    this.adminModuleId = adminModuleId;
  }

  public Long getAdminRoleId() {
    return adminRoleId;
  }

  public void setAdminRoleId(Long adminRoleId) {
    this.adminRoleId = adminRoleId;
  }
}
