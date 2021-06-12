package com.lishuai.entity.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 管理员使用 用户DTO
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Data
public class UserDTO {

    /**
     * 用户 id
     */
    private Integer userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

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
     * 积分
     */
    private Integer score;
}
