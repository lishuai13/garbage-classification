package com.lishuai.controller;


import com.lishuai.common.lang.Result;
import com.lishuai.entity.GarbageStation;
import com.lishuai.entity.vo.GarbageStationVO;
import com.lishuai.service.GarbageStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
@RequestMapping("/garbage-station")
public class GarbageStationController {

    @Autowired
    GarbageStationService garbageStationService;

    /**
     * 返回的是 GarbageStationVO
     * @return
     */
    @GetMapping("getStationInfo")
    public Result getStationInfo(){
        List<GarbageStationVO> stationInfo = garbageStationService.getStationInfo();
        log.info("查询垃圾站信息成功！");
        return Result.success(stationInfo);
    }

    /**
     * 返回的是 GarbageStation
     * @return
     */
    @GetMapping("/getStation")
    public Result getStation(){
        List<GarbageStation> stations = garbageStationService.getStation();
        log.info("查询垃圾站实体成功！");
        return Result.success(stations);
    }
}
