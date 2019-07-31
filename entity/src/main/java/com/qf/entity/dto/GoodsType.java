package com.qf.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qf.entity.base.BaseDto;

@TableName("goods_type")
public class GoodsType extends BaseDto {
  @TableField("name")
  private String name;
  private Long orderNum;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(Long orderNum) {
    this.orderNum = orderNum;
  }

}
