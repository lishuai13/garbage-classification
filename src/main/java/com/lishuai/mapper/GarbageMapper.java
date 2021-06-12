package com.lishuai.mapper;

import com.lishuai.entity.Garbage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface GarbageMapper extends BaseMapper<Garbage> {


}
