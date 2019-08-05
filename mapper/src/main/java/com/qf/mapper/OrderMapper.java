package com.qf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.dto.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author-izumi
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {
    boolean updatePayState(@Param("orderId") String orderId, @Param("stateCode") Long code);
}
