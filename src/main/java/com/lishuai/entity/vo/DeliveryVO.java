package com.lishuai.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 用户查询投递记录
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Data
public class DeliveryVO {


    /**
     * 投递 id
     */
    private Integer deliveryId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 垃圾分类名称
     */
    private String categoryName;

    /**
     * 垃圾站名称
     */
    private String stationName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
