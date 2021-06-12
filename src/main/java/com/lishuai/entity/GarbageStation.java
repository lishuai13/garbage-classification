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
@TableName(value = "garbage_station")
public class GarbageStation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 垃圾站表
     */
    @TableId(value = "station_id", type = IdType.AUTO)
    private Integer stationId;

    /**
     * 垃圾站名称
     */
    private String stationName;

    /**
     * 垃圾站地址
     */
    private String stationAddress;

    /**
     * 垃圾站描述
     */
    private String stationDescription;

    /**
     * 垃圾站图片
     */
    private String stationImage;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
