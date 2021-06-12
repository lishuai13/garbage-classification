package com.lishuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lishuai.entity.*;
import com.lishuai.entity.dto.DeliveryDTO;
import com.lishuai.entity.vo.DeliveryVO;
import com.lishuai.mapper.*;
import com.lishuai.service.DeliveryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
public class DeliveryServiceImpl extends ServiceImpl<DeliveryMapper, Delivery> implements DeliveryService {

    @Autowired
    DeliveryMapper deliveryMapper;

    @Autowired
    CapacityMapper capacityMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ClassificationMapper classificationMapper;

    @Autowired
    GarbageStationMapper garbageStationMapper;

    @Override
    public void saveDelivery(DeliveryDTO deliveryDTO) throws Exception {
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        QueryWrapper queryWrapper = new QueryWrapper();

        HashMap<String,Object> map = new HashMap<>();
        map.put("category_id",deliveryDTO.getCategoryId());
        map.put("station_id",deliveryDTO.getStationId());
        queryWrapper.allEq(map);
        Capacity capacity = capacityMapper.selectOne(queryWrapper);
        if (capacity.getCapacityNumber()<=0){
            throw new Exception("该垃圾站,该垃圾种类已无法投递,请换投其他垃圾站！");
        }
        Delivery delivery = new Delivery();
        delivery.setUserId(user.getUserId());
        delivery.setCategoryId(deliveryDTO.getCategoryId());
        delivery.setStationId(deliveryDTO.getStationId());
        deliveryMapper.insert(delivery);
        userMapper.addScore(user.getUserId());
        capacityMapper.reduceCapacity(deliveryDTO.getCategoryId(),deliveryDTO.getStationId());
    }

    @Override
    public List<DeliveryVO> getDelivery() {

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<DeliveryVO> result = new ArrayList<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",user.getUserId());
        queryWrapper.orderByDesc("create_time");
        List<Delivery> list = deliveryMapper.selectList(queryWrapper);
        list.forEach(v->{
            DeliveryVO deliveryVO = new DeliveryVO();
            deliveryVO.setUsername(user.getUsername());
            deliveryVO.setCreateTime(v.getCreateTime());
            Classification classification = classificationMapper.selectById(v.getCategoryId());
            GarbageStation garbageStation = garbageStationMapper.selectById(v.getStationId());
            deliveryVO.setCategoryName(classification.getCategoryName());
            deliveryVO.setStationName(garbageStation.getStationName());
            result.add(deliveryVO);
        });
        return result;
    }

    @Override
    public List<DeliveryVO> getAllDelivery() {
        List<DeliveryVO> result = new ArrayList<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        List<Delivery> list = deliveryMapper.selectList(queryWrapper);
        list.forEach(v->{
            DeliveryVO deliveryVO = new DeliveryVO();
            deliveryVO.setDeliveryId(v.getDeliveryId());
            deliveryVO.setCreateTime(v.getCreateTime());
            User user = userMapper.selectById(v.getUserId());
            deliveryVO.setUsername(user.getUsername());
            Classification classification = classificationMapper.selectById(v.getCategoryId());
            GarbageStation garbageStation = garbageStationMapper.selectById(v.getStationId());
            deliveryVO.setCategoryName(classification.getCategoryName());
            deliveryVO.setStationName(garbageStation.getStationName());
            result.add(deliveryVO);
        });
        return result;
    }

    @Override
    public List<DeliveryVO> getDeliveryById(int[] deliveryIds) {
        List<DeliveryVO> result = new ArrayList<>();
        for (int deliveryId : deliveryIds) {
            Delivery v = deliveryMapper.selectById(deliveryId);
            DeliveryVO deliveryVO = new DeliveryVO();
            deliveryVO.setDeliveryId(v.getDeliveryId());
            deliveryVO.setCreateTime(v.getCreateTime());
            User user = userMapper.selectById(v.getUserId());
            deliveryVO.setUsername(user.getUsername());
            Classification classification = classificationMapper.selectById(v.getCategoryId());
            GarbageStation garbageStation = garbageStationMapper.selectById(v.getStationId());
            deliveryVO.setCategoryName(classification.getCategoryName());
            deliveryVO.setStationName(garbageStation.getStationName());
            result.add(deliveryVO);
        }
        return result;
    }


}
