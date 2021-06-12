package com.lishuai.service.impl;

import com.lishuai.entity.GarbageStation;
import com.lishuai.entity.vo.GarbageStationVO;
import com.lishuai.mapper.GarbageStationMapper;
import com.lishuai.service.GarbageStationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Service
public class GarbageStationServiceImpl extends ServiceImpl<GarbageStationMapper, GarbageStation> implements GarbageStationService {

    @Autowired
    GarbageStationMapper garbageStationMapper;

    @Override
    public List<GarbageStationVO> getStationInfo() {
        return garbageStationMapper.getStationInfo();
    }

    @Override
    public List<GarbageStation> getStation() {
        List<GarbageStation> garbageStations = garbageStationMapper.selectList(null);
        return garbageStations;
    }

    @Override
    public void changeCapacity(int stationId, int categoryId, int capacityNumber) {
        garbageStationMapper.changeCapacity(stationId,categoryId,capacityNumber);
    }
}
