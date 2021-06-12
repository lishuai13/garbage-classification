package com.lishuai.service;

import com.lishuai.entity.Delivery;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lishuai.entity.dto.DeliveryDTO;
import com.lishuai.entity.vo.DeliveryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
public interface DeliveryService extends IService<Delivery> {
    /**
     * 新增投递
     * @param deliveryDTO
     */
    void saveDelivery(DeliveryDTO deliveryDTO) throws Exception;

    /**
     * 通过用户id 查询投递记录
     * @return
     */
    List<DeliveryVO> getDelivery();

    /**
     * 管理员查询所有投递记录
     * @return
     */
    List<DeliveryVO> getAllDelivery();

    /**
     * 查询投递记录并打印
     * @param deliveryIds
     * @return
     */
    List<DeliveryVO> getDeliveryById(int[] deliveryIds);
}
