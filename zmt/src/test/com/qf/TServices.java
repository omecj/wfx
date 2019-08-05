package com.qf;


import com.qf.entity.vo.OrderVo;
import com.qf.service.impl.OrderVoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @Author-izumi
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TServices {

    @Autowired
    private OrderVoServiceImpl orderVoService;

    @Test
    public void testMapper(){
        List<OrderVo> list = orderVoService.getOrderByMerchantUserId(1L);
        list.forEach(p-> System.out.println(p));


    }
}
