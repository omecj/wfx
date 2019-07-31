package com.qf.zmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @Author-izumi
 */
@Controller
public class FileUpLoad {

    @RequestMapping("upload")
    @ResponseBody
    public String toUpload(MultipartFile file) throws IOException {
        int start = file.getName().lastIndexOf(".");
        String filename = new Date().toString()+file.getName().substring(start);
        file.transferTo(new File("C:\\Users\\Wu\\Desktop\\新建文件夹"+filename));
        return "ok";
    }
}
