package com.lishuai.mapper;

import com.lishuai.entity.Complaint;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

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
public interface ComplaintMapper extends BaseMapper<Complaint> {
    /**
     * 管理员回复投诉
     * @param complaintId
     * @param answer
     */
    void answerComplaint(int complaintId,String answer);
}
