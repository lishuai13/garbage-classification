package com.lishuai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lishuai.entity.User;
import com.lishuai.entity.dto.RegisterDTO;
import com.lishuai.entity.dto.UserDTO;
import com.lishuai.mapper.UserMapper;
import com.lishuai.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lishuai.utils.SaltUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public void register(RegisterDTO registerDto) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",registerDto.getUsername());
        User result = userMapper.selectOne(queryWrapper);
        if (result!=null){
            throw new Exception("用户名已存在！");
        }
        User temp = new User();
        //生成随机盐
        String salt = SaltUtils.getSalt(8);
        //明文密码进行md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(registerDto.getPassword(),salt,1024);
        registerDto.setPassword(md5Hash.toHex());
        BeanUtil.copyProperties(registerDto, temp);
        //将随机盐保存到数据
        temp.setSalt(salt);
        userMapper.insert(temp);
    }

    @Override
    public void editUser(User user) {

        HashMap<String,Object>map = new HashMap<>();
        map.put("userId",user.getUserId());
        map.put("telephone",user.getTelephone());
        map.put("email",user.getEmail());
        userMapper.editUser(map);
    }

    @Override
    public void updateAvatar(int userId, String avatar) {
        userMapper.updateAvatar(userId,avatar);
    }

    @Override
    public void updatePassword(String oldPassword,String newPassword) throws Exception {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Md5Hash md5Hash = new Md5Hash(oldPassword,user.getSalt(),1024);
        if (!user.getPassword().equals(md5Hash.toHex())){
            throw new Exception("原密码错误!");
        }else{
            //生成随机盐
            String salt = SaltUtils.getSalt(8);
            //明文密码进行md5 + salt + hash散列
            Md5Hash md5Hash2 = new Md5Hash(newPassword,salt,1024);
            userMapper.updatePassword(user.getUserId(),md5Hash2.toHex(),salt);
        }
        log.info("用户修改密码成功！");
    }

    @Override
    public void adminEditUser(UserDTO user) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",user.getUsername());
        User result = userMapper.selectOne(queryWrapper);
        String username =  userMapper.selectById(user.getUserId()).getUsername();
        if (result!=null&& !username.equals(user.getUsername())){
            throw new Exception("用户名已存在！");
        }else{
            HashMap<String,Object>map = new HashMap<>();
            map.put("userId",user.getUserId());
            map.put("username",user.getUsername());
            map.put("telephone",user.getTelephone());
            map.put("email",user.getEmail());
            map.put("score",user.getScore());
            userMapper.adminEditUser(map);
        }
        log.info("管理员编辑用户成功！");
    }

    @Override
    public void changUserStatus(int userId, Boolean status) {
        int num;
        if (!status){
            num=0;
        }else {
            num=1;
        }
        userMapper.changUserStatus(userId,num);
    }

}
