package com.qf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.vo.OrderVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author-izumi
 */
@Repository
public interface OrderVoMapper extends BaseMapper<OrderVo> {
    List<OrderVo> getOrderByMerchantUserId(@Param("merchantUserId") Long id);

    OrderVo getByOrderId(@Param("orderId") Long id);
}
