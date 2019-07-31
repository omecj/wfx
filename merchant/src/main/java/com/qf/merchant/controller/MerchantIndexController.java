package com.qf.merchant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author-izumi
 */
@Controller
public class MerchantIndexController {

    @RequestMapping("toIndex")
    public String toIndex(){
        return "index";
    }
}
