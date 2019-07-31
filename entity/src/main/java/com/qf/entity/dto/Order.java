package com.qf.entity.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qf.entity.base.BaseDto;

import java.util.Date;
@TableName("order")
public class Order extends BaseDto {
  @TableField("order_id")
  private String orderId;
  private String phone;
  private String name;
  private Long goodId;
  private Long num;
  private String province;
  private String city;
  private String area;
  private String address;
  private String remark;
  private Long payType;
  private Long skuId;
  private Long payState;
  private Date createTime;

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getGoodId() {
    return goodId;
  }

  public void setGoodId(Long goodId) {
    this.goodId = goodId;
  }

  public Long getNum() {
    return num;
  }

  public void setNum(Long num) {
    this.num = num;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Long getPayType() {
    return payType;
  }

  public void setPayType(Long payType) {
    this.payType = payType;
  }

  public Long getSkuId() {
    return skuId;
  }

  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  public Long getPayState() {
    return payState;
  }

  public void setPayState(Long payState) {
    this.payState = payState;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}
