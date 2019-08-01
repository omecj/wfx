package com.qf.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.common.http.Result;
import com.qf.entity.dto.Goods;
import com.qf.service.impl.GoodsServiceImpl;
import com.qf.service.impl.GoodsTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String toGoods(Model model,@RequestParam(value = "id",required = false)Long id){
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        if (id != null){
            wrapper.lambda().eq(Goods::getGoodsTypeId, id);
        }
        wrapper.lambda().orderByAsc(Goods::getCreateTime);
        model.addAttribute("goodsTypes", goodsTypeService.list());
        model.addAttribute("goodsList", goodsService.list(wrapper));
        return "goods/list";
    }

    @PostMapping("up")
    @ResponseBody
    public Result<?> toUp(@RequestParam(value = "id",required = true) Long id){
        goodsService.up(id);
        return Result.success();
    }

    @PostMapping("down")
    @ResponseBody
    public Result<?> toDown(@RequestParam(value = "id",required = true) Long id){
        goodsService.down(id);
        return Result.success();
    }

    @PostMapping("del")
    @ResponseBody
    public Result<?> toDel(@RequestParam(value = "id",required = true) Long id){
        goodsService.del(id);
        return Result.success();
    }
}
