package com.qf.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.common.http.Result;
import com.qf.entity.dto.MerchantUser;
import com.qf.service.impl.MerchantUserServiceImpl;
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
@RequestMapping("merchant")
public class MerchantController {

    @Autowired
    private MerchantUserServiceImpl merchantUserService;

    @RequestMapping("user")
    public String toList(Model model, @RequestParam(value = "key", required = false) String key) {
        if (key == null) {
            model.addAttribute("merchantUserList", merchantUserService.list());
        }else{
            QueryWrapper<MerchantUser> wrapper = new QueryWrapper<>();
            wrapper.lambda().like(MerchantUser::getName, key);
            model.addAttribute("merchantUserList", merchantUserService.list(wrapper));
        }
        return "merchant/user";
    }

    @PostMapping("save")
    @ResponseBody
    public Result<?> toSave(MerchantUser user){
        merchantUserService.saveOrUpdate(user);
        return Result.success();
    }

    @PostMapping("del")
    @ResponseBody
    public Result<?> toDel(Long id){
        merchantUserService.removeById(id);
        return Result.success();
    }

    @PostMapping("getUserById")
    @ResponseBody
    public Result<?> idToUser(@RequestParam(value = "id",required = true) Long id){
        return Result.success(merchantUserService.getById(id));
    }

    @PostMapping("search")
    public String toSearch(Model model,String key){
        QueryWrapper<MerchantUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().like(MerchantUser::getName,key);
        model.addAttribute("merchantUserList", merchantUserService.list(wrapper));
        System.out.println("=============================");
        return "merchant/user1";
    }

}
