package com.lishuai.service;

import com.lishuai.entity.Complaint;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lishuai.entity.dto.ComplaintDTO;
import com.lishuai.entity.vo.ComplaintVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
public interface ComplaintService extends IService<Complaint> {

    /**
     * 用户新增投诉
     * @param complaintDTO
     */
    void saveComplaint(ComplaintDTO complaintDTO);

    /**
     * 用户查询投诉记录
     * @return
     */
    List<ComplaintVO> getComplaint();

    /**
     * 管理员查询所有投诉信息,传入 0 就是未回复的，传入 1 就是已经回复的
     * @param flag
     * @return
     */
    List<ComplaintVO> getAllComplaint(int flag);

    /**
     * 管理员回复投诉
     * @param complaintId
     * @param answer
     */
    void answerComplaint(int complaintId,String answer);
}
