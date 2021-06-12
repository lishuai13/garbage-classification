package com.lishuai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lishuai.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Mapper
@Component
public interface NoticeMapper extends BaseMapper<Notice> {
}
