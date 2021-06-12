package com.lishuai.entity.vo;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 用户查询投诉记录
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Data
public class ComplaintVO {

    /**
     * 投诉id
     */
    private Integer complaintId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 回复
     */
    private String answer;

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
