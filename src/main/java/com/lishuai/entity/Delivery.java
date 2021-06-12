package com.lishuai.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName(value = "delivery")
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 投递信息
     */
    @TableId(value = "delivery_id", type = IdType.AUTO)
    private Integer deliveryId;

    /**
     * 投递人
     */
    private Integer userId;

    /**
     * 垃圾种类
     */
    private Integer categoryId;

    /**
     * 垃圾站
     */
    private Integer stationId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
