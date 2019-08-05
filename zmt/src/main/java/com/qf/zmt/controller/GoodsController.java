package com.qf.zmt.controller;

import com.qf.entity.po.LoginMerchant;
import com.qf.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author-izumi
 */
@Controller
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsServiceImpl goodsService;

    @RequestMapping("list")
    public String toList(Model model, LoginMerchant loginMerchant) {
        model.addAttribute("goodsList", goodsService.getByMerchantIdAndStatus(loginMerchant.getId()));
        model.addAttribute("totalSale", 1020);
        return "goods/list";
    }

}
