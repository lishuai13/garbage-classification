package com.lishuai.mapper;

import com.lishuai.entity.Capacity;
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
public interface CapacityMapper extends BaseMapper<Capacity> {

    /**
     * 投递成功，对应的垃圾站的垃圾种类容量减 1
     * @param categoryId
     * @param stationId
     */
    void reduceCapacity(int categoryId ,int stationId);

}
