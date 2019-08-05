package com.qf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.entity.vo.OrderVo;
import com.qf.mapper.OrderVoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author-izumi
 */
@Service
public class OrderVoServiceImpl extends ServiceImpl<OrderVoMapper, OrderVo> {
    public List<OrderVo> getOrderByMerchantUserId(Long id) {
        return this.baseMapper.getOrderByMerchantUserId(id);
    }

    public OrderVo getByOrderId(Long id) {
        return this.baseMapper.getByOrderId(id);
    }
}
