package com.lishuai.service;

import com.lishuai.entity.GarbageStation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lishuai.entity.vo.GarbageStationVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
public interface GarbageStationService extends IService<GarbageStation> {
    /**
     * 获取垃圾站容量信息
     * @return
     */
    List<GarbageStationVO> getStationInfo();

    /**
     * 获取实体信息
     * @return
     */
    List<GarbageStation> getStation();

    /**
     * 管理员修改垃圾站容量
     * @param stationId
     * @param categoryId
     * @param capacityNumber
     */
    void changeCapacity(int stationId,int categoryId,int capacityNumber);
}
