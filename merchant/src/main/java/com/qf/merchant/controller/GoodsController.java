package com.qf.merchant.controller;

import com.qf.common.http.Result;
import com.qf.entity.dto.Goods;
import com.qf.entity.po.LoginMerchant;
import com.qf.service.impl.GoodsServiceImpl;
import com.qf.service.impl.GoodsSkuServiceImpl;
import com.qf.service.impl.GoodsTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author-izumi
 */
@Controller
@RequestMapping("goods")
public class GoodsController {

    @Value("${path.address}")
    private String address;
    @Value("${path.ip-port}")
    private String ip;


    @Autowired
    private GoodsTypeServiceImpl goodsTypeService;
    @Autowired
    private GoodsServiceImpl goodsService;
    @Autowired
    private GoodsSkuServiceImpl goodsSkuService;

    @RequestMapping("list")
    public String toList(Model model, LoginMerchant loginMerchant) {
        model.addAttribute("goodsList", goodsService.getByMerchantId(loginMerchant.getId()));
        return "goods/list";
    }


    @PostMapping("del")
    @ResponseBody
    public Result<?> toDel(@RequestParam(value = "id", required = true) Long id) {
        goodsService.removeById(id);
        return Result.success();
    }

    @RequestMapping({"add", "edit"})
    public String toAdd(Model model,@RequestParam(value = "id",required = false) Long id) {
        if (id != null){
            model.addAttribute("goods", goodsService.getById(id));
            model.addAttribute("skuList", goodsSkuService.getByGoodsId(id));
        }
        model.addAttribute("goodsTypeList", goodsTypeService.list());
        return "goods/add";
    }

    @PostMapping("save")
    @ResponseBody
    public Result<?> toSave(Goods goods,LoginMerchant loginMerchant) {
        goodsService.saveGoods(goods, loginMerchant);
        return Result.success();
    }

    @PostMapping("upload")
    @ResponseBody
    public Result<?> toUpload(MultipartFile[] files) throws IOException {
        List<String> list = new ArrayList<>(files.length);
        for (MultipartFile file : files) {
            String s = saveUp(file);
            System.out.println(s);
            list.add(s);
        }
        return Result.success(list);
    }
    //多文件上传
    private String saveUp(MultipartFile file) throws IOException {
        int start = file.getOriginalFilename().lastIndexOf(".");
        String pic = String.format("%s%s", UUID.randomUUID().toString(),
                file.getOriginalFilename().substring(start));
        file.transferTo(new File(String.format("%s%s", address,pic)));
        return String.format("%s%s", ip,pic);
    }
}
