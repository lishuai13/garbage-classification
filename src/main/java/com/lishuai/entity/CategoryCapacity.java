package com.lishuai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * <p>
 * 此实体类存在数据库中，仅用于接受数据库对象，传递数据给 GarbageStationVO
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Data
public class CategoryCapacity {

    /**
     * 垃圾分类信息
     */
    private Integer categoryId;
    
    /**
     * 垃圾分类名称
     */
    private String categoryName;

    /**
     * 容量
     */
    private Integer capacityNumber;
}
