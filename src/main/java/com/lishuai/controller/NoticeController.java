package com.lishuai.controller;

import com.lishuai.common.lang.Result;
import com.lishuai.entity.Notice;
import com.lishuai.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @GetMapping("/getNotice")
    public Result getNotice(){
        Notice notice = noticeService.getNotice();
        log.info("获取公告成功！");
        return Result.success(notice);
    }
}
