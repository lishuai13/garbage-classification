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
@TableName(value = "garbage")
public class Garbage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 垃圾表
     */
    @TableId(value = "garbage_id", type = IdType.AUTO)
    private Integer garbageId;

    /**
     * 垃圾名
     */
    private String garbageName;

    /**
     * 垃圾描述
     */
    private String garbageDescription;

    /**
     * 垃圾分类
     */
    private Integer categoryId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
