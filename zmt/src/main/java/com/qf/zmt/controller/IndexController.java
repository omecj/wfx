package com.qf.zmt.controller;

import com.qf.common.http.Result;
import com.qf.entity.vo.JqueryTreeView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author-izumi
 */
@Controller
public class IndexController {

    @RequestMapping("index")
    public String toIndex(){
        return "index";
    }

    @RequestMapping("func-list")
    @ResponseBody
    public Result<List<JqueryTreeView>> getData(){
        List<JqueryTreeView> list = new ArrayList<>();
        list.add(new JqueryTreeView(1,"商品推广","goods/list"));
        list.add(new JqueryTreeView(2,"订单管理","order/list"));
        return Result.success(list);
    }
}
