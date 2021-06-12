package com.lishuai.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 用户VO,管理员页面使用
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Data
public class UserVO {

    /**
     * 用户 id
     */
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
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 积分，默认为 0
     */
    private Integer score;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
    private Boolean status;
}
