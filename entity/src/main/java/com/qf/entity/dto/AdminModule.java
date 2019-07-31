package com.qf.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qf.entity.base.BaseDto;

@TableName("admin_module")
public class AdminModule extends BaseDto {
  @TableField("name")
  private String name;
  private String url;
  private Long orderNum;
  private Long parentId;

  public AdminModule(Long Id) {
    super();
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }

  public Long getOrderNum() {
    return orderNum;
  }
  public void setOrderNum(Long orderNum) {
    this.orderNum = orderNum;
  }

  public Long getParentId() {
    return parentId;
  }
  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

}
