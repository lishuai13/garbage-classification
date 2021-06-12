package com.lishuai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lishuai.entity.Garbage;
import com.lishuai.entity.User;
import com.lishuai.entity.dto.RegisterDTO;
import com.lishuai.mapper.ComplaintMapper;
import com.lishuai.mapper.DeliveryMapper;
import com.lishuai.mapper.GarbageMapper;
import com.lishuai.mapper.UserMapper;
import com.lishuai.service.AdminService;
import com.lishuai.service.OssService;
import com.lishuai.utils.JsonUtils;
import com.lishuai.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final static String URL = "http://localhost:6001";

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    GarbageMapper garbageMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ComplaintMapper complaintMapper;

    @Autowired
    DeliveryMapper deliveryMapper;

    @Autowired
    OssService ossService;

    @Override
    public List<User> getAllUser(String keywords) {
        return userMapper.getAllUser(keywords);
    }

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
        //管理员注册用户默认密码为123456
        Md5Hash md5Hash = new Md5Hash("123456",salt,1024);
        registerDto.setPassword(md5Hash.toHex());
        BeanUtil.copyProperties(registerDto, temp);
        //将随机盐保存到数据
        temp.setSalt(salt);
        userMapper.insert(temp);
    }

    @Override
    public void deleteUser(int userId) {

        User user = userMapper.selectById(userId);

        String avatar = user.getAvatar();
        String[] split = avatar.split("/");
        String fileName = split[split.length-1];
        if (!"default-avatar.png".equals(fileName)){
            restTemplate.getForObject(URL+"/oss/rest/deleteFile/"+fileName,String.class);
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",user.getUserId());
        deliveryMapper.delete(queryWrapper);
        complaintMapper.delete(queryWrapper);
        userMapper.deleteById(userId);
    }

    @Override
    public Boolean addGarbageToEls() throws IOException {
        // 解析查询出来的数据
        List<Garbage> contents = garbageMapper.selectList(null);
        // 封装数据到索引库中！
        BulkRequest bulkRequest = new BulkRequest();
        //超时限制
        bulkRequest.timeout(TimeValue.timeValueMinutes(2));
        bulkRequest.timeout("2m");
        //将文档批量添加到已经建立好的索引，id随机生成
        for (Garbage content : contents) {
            bulkRequest.add(new IndexRequest("garbage").source(JsonUtils.getJson(content), XContentType.JSON));
        }
        //执行请求
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        //返回结果
        return !bulkResponse.hasFailures();
    }
}
