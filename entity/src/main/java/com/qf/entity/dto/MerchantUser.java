package com.qf.entity.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qf.entity.base.BaseDto;

import java.util.Date;
@TableName("merchant_user")
public class MerchantUser extends BaseDto {
  @TableField("name")
  private String name;
  private String userName;
  private String password;
  private String qq;
  private String wxh;
  private String phone;
  private Date createTime;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getQq() {
    return qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }

  public String getWxh() {
    return wxh;
  }

  public void setWxh(String wxh) {
    this.wxh = wxh;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "MerchantUser{" +
            "name='" + name + '\'' +
            ", userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            ", qq='" + qq + '\'' +
            ", wxh='" + wxh + '\'' +
            ", phone='" + phone + '\'' +
            ", createTime=" + createTime +
            '}';
  }
}
