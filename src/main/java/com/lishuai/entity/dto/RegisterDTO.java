package com.lishuai.entity.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * <p>
 * 用户注册 DTO
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Data
public class RegisterDTO {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @Size(min=6,max=16,message = "密码长度应在6-16位")
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
     * 头像
     */
    private String avatar;
}
