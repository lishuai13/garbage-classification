package com.lishuai.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lishuai.common.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

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
@TableName(value = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户表
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    @Pattern(regexp = "^1[3|4|5|7|8][0-9]{9}$",message = "请输入有效的电话号码")
    private String telephone;

    /**
     * 邮箱
     */
    @Email(message = "请输入正确有效的邮箱")

    private String email;

    /**
     * 积分，默认为 0
     */
    private Integer score;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 加密用的随机盐
     */
    private String salt;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 角色,数据库不存在该字段
     */
    @TableField(exist = false)
    private Role role;
}
