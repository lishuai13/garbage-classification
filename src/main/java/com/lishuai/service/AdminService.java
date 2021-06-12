package com.lishuai.service;

import com.lishuai.entity.User;
import com.lishuai.entity.dto.RegisterDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
public interface AdminService {

    /**
     * 查询所有非管理员用户
     * @param keywords
     * @return
     */
    List<User> getAllUser(String keywords);

    /**
     * 用户注册
     * @param registerDto
     */
    void register(RegisterDTO registerDto) throws Exception;

    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(int userId);

    /**
     * 将垃圾信息存入Els
     */
    Boolean addGarbageToEls() throws IOException;
}
