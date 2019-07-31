package com.qf.admin.controller;

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

    @Value("${path.ip-port}")
    private String ip;

    @Value("${path.address}")
    private String address;

    @RequestMapping("toUpload")
    public String toUpload(){
        return "upload";
    }

    @RequestMapping("upload")
    @ResponseBody
    public String toUpload(MultipartFile file) throws IOException {
        int start = file.getOriginalFilename().lastIndexOf(".");
        String pic= UUID.randomUUID().toString() +file.getOriginalFilename().substring(start);
        String filename = address+ pic;
        System.out.println(filename);
        file.transferTo(new File(filename));
        return ip + pic;
    }
}
