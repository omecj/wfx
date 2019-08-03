package com.qf.zmt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author-izumi
 */
@Controller
public class FileUpLoad {

    @Value("${path.address}")
    private String address;

    @RequestMapping({"tt"})
    public String up(){
        return "tt";
    }

    @RequestMapping("upload")
    @ResponseBody
    public String toUpload(MultipartFile file) throws IOException {
        int start = file.getOriginalFilename().lastIndexOf(".");
        String filename = UUID.randomUUID().toString()+file.getOriginalFilename().substring(start);
        file.transferTo(new File(String.format("%s%s",address,filename)));
        System.out.println(filename);
        return "ok";
    }
}
