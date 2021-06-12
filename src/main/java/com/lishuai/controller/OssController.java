package com.lishuai.controller;

import com.lishuai.common.lang.Result;
import com.lishuai.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 上传文件
 * @author lishuai
 */
@RestController
@RequestMapping("/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 上传文件
     * @param file
     * @param dirName
     * @param fileName
     * @return
     */
    @PostMapping("/uploadAvatar")
    public Result uploadOssFile(@RequestParam("file") MultipartFile file,
                                @RequestParam("dirName") String dirName,
                                @RequestParam("fileName") String fileName) {
        //获取上传文件 MultipartFile
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = ossService.uploadFileAvatar(inputStream,dirName,fileName,file.getOriginalFilename());

        return Result.success("文件上传成功->"+url);
    }

    @PostMapping("/test")
    public void test(@RequestParam("file") String name) {
        System.out.println("name----------------"+name);
    }

    @PostMapping("/deleteFile")
    public Result deleteFile(@RequestParam("fileName") String fileName) {
        ossService.deleteFile(fileName);
        return Result.success("删除成功！");
    }
}
