package com.lishuai.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lishuai.entity.CategoryCapacity;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 用户查询投递记录
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Data
public class GarbageStationVO {

    /**
     * 垃圾站表
     */
    private Integer stationId;

    /**
     * 垃圾站名称
     */
    private String stationName;

    /**
     * 垃圾种类及其对应的容量对象集合
     */
    private List<CategoryCapacity>categoryCapacitys;
}
