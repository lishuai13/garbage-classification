package com.lishuai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lishuai.entity.Complaint;
import com.lishuai.entity.User;
import com.lishuai.entity.dto.ComplaintDTO;
import com.lishuai.entity.vo.ComplaintVO;
import com.lishuai.mapper.CapacityMapper;
import com.lishuai.mapper.ComplaintMapper;
import com.lishuai.mapper.UserMapper;
import com.lishuai.service.ComplaintService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
@Slf4j
public class ComplaintServiceImpl extends ServiceImpl<ComplaintMapper, Complaint> implements ComplaintService {

    @Autowired
    ComplaintMapper complaintMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public void saveComplaint(ComplaintDTO complaintDTO) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Complaint complaint = new Complaint();
        complaint.setUserId(user.getUserId());
        BeanUtil.copyProperties(complaintDTO,complaint);
        complaintMapper.insert(complaint);
    }

    @Override
    public List<ComplaintVO> getComplaint() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<ComplaintVO>result = new ArrayList<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",user.getUserId());
        queryWrapper.orderByDesc("create_time");
        List<Complaint> list = complaintMapper.selectList(queryWrapper);
        list.forEach(v->{
            ComplaintVO complaintVO = new ComplaintVO();
            complaintVO.setComplaintId(v.getComplaintId());
            complaintVO.setUsername(user.getUsername());
            BeanUtil.copyProperties(v,complaintVO);
            result.add(complaintVO);
        });
        return result;
    }

    @Override
    public List<ComplaintVO> getAllComplaint(int flag) {
        List<ComplaintVO>result = new ArrayList<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("flag",flag);
        queryWrapper.orderByDesc("update_time");
        List<Complaint> list = complaintMapper.selectList(queryWrapper);
        list.forEach(v->{
            ComplaintVO complaintVO = new ComplaintVO();
            complaintVO.setComplaintId(v.getComplaintId());
            User user = userMapper.selectById(v.getUserId());
            complaintVO.setUsername(user.getUsername());
            BeanUtil.copyProperties(v,complaintVO);
            result.add(complaintVO);
        });
        return result;
    }

    @Override
    public void answerComplaint(int complaintId, String answer) {
        complaintMapper.answerComplaint(complaintId,answer);
        log.info("管理员回复投诉成功！");
    }

}
