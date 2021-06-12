package com.lishuai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "capacity")
public class Capacity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 垃圾站容量表
     */
    @TableId(value = "capacity_id", type = IdType.AUTO)
    private Integer capacityId;

    /**
     * 垃圾站
     */
    private Integer stationId;

    /**
     * 垃圾种类
     */
    private Integer categoryId;

    /**
     * 容量
     */
    private Integer capacityNumber;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
