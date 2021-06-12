package com.lishuai.mapper;

import com.lishuai.entity.GarbageStation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lishuai.entity.vo.GarbageStationVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
public interface GarbageStationMapper extends BaseMapper<GarbageStation> {

    /**
     * 获取垃圾站容量信息
     * @return
     */
    List<GarbageStationVO> getStationInfo();

    /**
     * 管理员修改垃圾站容量
     * @param stationId
     * @param categoryId
     * @param capacityNumber
     */
    void changeCapacity(int stationId,int categoryId,int capacityNumber);
}
