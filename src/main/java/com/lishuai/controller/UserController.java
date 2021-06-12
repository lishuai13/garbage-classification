package com.lishuai.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lishuai.entity.Student;
import com.lishuai.entity.dto.LoginDTO;
import com.lishuai.common.lang.Result;
import com.lishuai.entity.User;
import com.lishuai.entity.dto.RegisterDTO;
import com.lishuai.service.UserService;
import com.lishuai.shiro.jwt.JWTToken;
import com.lishuai.utils.JwtUtils;
import com.lishuai.utils.SaltUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Size;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody @Validated LoginDTO account, HttpServletResponse response){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",account.getUsername());
        User result = userService.getOne(queryWrapper);
        if (result==null){
            throw new AuthenticationException("用户不存在!");
        }
        Md5Hash md5Hash = new Md5Hash(account.getPassword(),result.getSalt(),1024);
        Subject subject = SecurityUtils.getSubject();
        String token = JwtUtils.sign(account.getUsername(),md5Hash.toHex());
        JWTToken jwtToken = new JWTToken(token);
        subject.login(jwtToken);

        User user = (User) subject.getPrincipal();
        log.info("用户登录！");
        //返回token和用户信息
        response.setHeader("token", token);
        response.setHeader("Access-control-Expose-Headers", "token");
        return Result.success(user);
        // todo 多用户登陆问题
    }

    @GetMapping("/logout")
    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        log.info("用户登出！");
    }

    @PostMapping("/register")
    public Result register(@RequestBody @Validated RegisterDTO registerDto) throws Exception {
        userService.register(registerDto);
        log.info("用户注册成功！");
        return Result.success();
    }

    /**
     * 用户修改用户信息，不能用于修改密码
     * @param user
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/editUser")
    public Result editUser(@RequestBody @Validated User user){
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        if (!user.getUserId().equals(principal.getUserId())){
            throw new AuthenticationException("只能修改本人信息！");
        }
        userService.editUser(user);
        log.info("用户修改信息成功！");
        return Result.success();
        //todo 这里使用的xml文件中，逗号遗留的问题待解决,修改时必须带上头像信息,且不能修改密码
    }

    /**
     * 用户修改密码
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestParam("oldPassword")String oldPassword,
                                 @Size(min=6,max=16,message = "密码长度应在6-16位") @RequestParam("newPassword")String newPassword) throws Exception {

        userService.updatePassword(oldPassword,newPassword);
        return Result.success("修改密码成功！");
    }


    @PostMapping("/test")
    public Result test(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        log.info("当前用户信息-》{}",user.toString());
        return Result.success(user);
    }
}
