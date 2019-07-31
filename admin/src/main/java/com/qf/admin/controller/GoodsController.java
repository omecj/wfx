package com.qf.admin.controller;

import com.qf.service.impl.GoodsServiceImpl;
import com.qf.service.impl.GoodsTypeServiceImpl;
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
    private GoodsTypeServiceImpl goodsTypeService;
    @Autowired
    private GoodsServiceImpl goodsService;


    @RequestMapping("list")
    public String toGoods(Model model){
        model.addAttribute("goodsTypes", goodsTypeService.list());
        model.addAttribute("goodsList", goodsService.list());
        return "goods/list";
    }
}
