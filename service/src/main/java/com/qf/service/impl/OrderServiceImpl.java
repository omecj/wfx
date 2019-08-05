package com.qf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.entity.dto.Order;
import com.qf.entity.po.LoginMerchant;
import com.qf.mapper.OrderMapper;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author-izumi
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> {

    private final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

    @Autowired
    private GoodsSkuServiceImpl goodsSkuService;

    public void createOrder(Order order, LoginMerchant loginMerchant) {
        order.setOrderId(String.format("%s%s%s",
                SIMPLE_DATE_FORMAT.format(new Date()),  //时间戳
                loginMerchant.getId(), //商户id
                RandomUtils.nextInt(10000, 100000)) //五位随机数
        );
        order.setMerchantUserId(loginMerchant.getId());
        order.setPrice((int) (Integer.parseInt(goodsSkuService.getById(order.getSkuId()).getPrice()) * order.getNum()));
        this.save(order);
    }

    public boolean updatePayState(String orderId, Long code) {
        return this.baseMapper.updatePayState(orderId,code);
    }
}
