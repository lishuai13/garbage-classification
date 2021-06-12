package com.lishuai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lishuai.entity.Notice;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
public interface NoticeService extends IService<Notice> {

    /**
     * 获取系统公告
     * @return
     */
    Notice getNotice();
}
