package com.qf.admin.controller;

import com.qf.common.http.Result;
import com.qf.entity.po.LoginAdmin;
import com.qf.entity.vo.JqueryTreeView;
import com.qf.service.impl.AdminViewObjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author-izumi
 */
@Controller
public class IndexController {
    @Autowired
    private AdminViewObjectServiceImpl adminViewObjectService;

    @RequestMapping("toIndex")
    public String toIndex(){
        return "index";
    }


    @RequestMapping("func-list")
    @ResponseBody
    public Result<List<JqueryTreeView>> getData(LoginAdmin loginAdmin) {
        return Result.success(adminViewObjectService.getModuleByAdminId(loginAdmin.getId()));
    }

}
