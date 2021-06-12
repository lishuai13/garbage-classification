package com.lishuai.service;

import com.lishuai.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lishuai.entity.dto.RegisterDTO;
import com.lishuai.entity.dto.UserDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param registerDto
     */
    void register(RegisterDTO registerDto) throws Exception;

    /**
     * 修改用户信息
     * @param user
     */
    void editUser(User user);

    /**
     * 修改用户头像
     * @param userId
     * @param avatar
     */
    void updateAvatar(int userId,String avatar);

    /**
     * 用户修改密码
     * @param oldPassword
     * @param newPassword
     * @throws Exception
     */
    void updatePassword(String oldPassword,String newPassword) throws Exception;

    /**
     * 管理员修改用户信息
     * @param user
     * @throws Exception
     */
    void adminEditUser(UserDTO user) throws Exception;

    /**
     * 操作用户状态
     * @param userId
     * @param status
     */
    void changUserStatus(int userId,Boolean status);
}
