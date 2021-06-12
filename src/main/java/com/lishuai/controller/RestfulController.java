package com.lishuai.controller;


import cn.hutool.core.io.FileUtil;
import com.lishuai.common.lang.Result;
import com.lishuai.entity.User;
import com.lishuai.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * 上传文件 rest
 * @author lishuai
 */
@RestController
@RequestMapping("/consumerRest")
public class RestfulController {

    private final static String URL = "http://localhost:6001";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    UserService userService;

    @RequiresAuthentication
    @PostMapping("/uploadAvatar")
    public Result uploadOssFile(@RequestParam("file")MultipartFile file) {

        System.out.println("进入方法-----------");

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        // 临时文件保存路径
        File fileTemp = FileUtil.mkdir("E:\\File" + "\\" + file.getOriginalFilename());
        // 转存文件
        try {
            file.transferTo(fileTemp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(fileTemp.getPath());

        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);

        //设置请求体，注意是LinkedMultiValueMap
        FileSystemResource fileSystemResource = new FileSystemResource(new File(fileTemp.getPath()));
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("file", fileSystemResource);
        form.add("fileName",user.getUsername()+"-"+user.getUserId());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(form, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(URL+"/oss/rest/uploadAvatar", requestEntity, String.class);
        FileUtil.del(fileTemp);
        System.out.println(result.getBody());
        userService.updateAvatar(user.getUserId(),result.getBody());

        return Result.success();
    }

    @PostMapping("/test/{name}")
    public void test(@PathVariable String name) {
        restTemplate.getForObject(URL+"/oss/rest/test/"+name,void.class);
    }

//    @GetMapping("/toExcel")
//    public void prodTest() {
//        restTemplate.getForObject(URL+"/excel/toExcel",void.class);
//    }
}
