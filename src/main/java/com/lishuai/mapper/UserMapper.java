package com.lishuai.mapper;

import com.lishuai.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lishuai.entity.dto.RegisterDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {


    /**
     * 查找用户数据，包括角色数据
     * @param username
     * @return
     */
    User findRolesByUserName(String username);

    /**
     * 用户投递成功后，积分+1
     * @param userId
     */
    void addScore(int userId);

    /**
     * 修改用户信息
     * @param map
     */
    void editUser(Map map);

    /**
     * 管理员搜索用户
     * @param keywords
     * @return
     */
    List<User> getAllUser(String keywords);

    /**
     * 修改用户头像
     * @param userId
     * @param avatar
     */
    void updateAvatar(int userId,String avatar);

    /**
     * 用户修改密码
     * @param userId
     * @param password
     * @param salt
     */
    void updatePassword(int userId,String password,String salt);

    /**
     * 管理员修改用户信息
     * @param map
     */
    void adminEditUser(Map map);

    /**
     * 操作用户状态
     * @param userId
     * @param status
     */
    void changUserStatus(int userId,int status);

}
