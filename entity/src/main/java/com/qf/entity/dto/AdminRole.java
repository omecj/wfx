package com.qf.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qf.entity.base.BaseDto;

import java.util.Date;

@TableName("admin_role")
public class AdminRole extends BaseDto {
  @TableField("name")
  private String name;
  private String description;
  private Long orderNum;
  private Date createTime;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(Long orderNum) {
    this.orderNum = orderNum;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "AdminRole{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", orderNum=" + orderNum +
            ", createTime=" + createTime +
            ", id=" + id +
            '}';
  }
}