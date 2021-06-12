package com.lishuai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lishuai.entity.Notice;
import com.lishuai.mapper.NoticeMapper;
import com.lishuai.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public Notice getNotice() {
        return noticeMapper.selectById(1);
    }
}
