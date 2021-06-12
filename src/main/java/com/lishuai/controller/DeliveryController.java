package com.lishuai.controller;


import com.lishuai.common.lang.Result;
import com.lishuai.entity.dto.DeliveryDTO;
import com.lishuai.entity.vo.DeliveryVO;
import com.lishuai.service.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Slf4j
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @GetMapping("/test")
    public Result test(){
        return Result.success("进入方法成功");
    }


    @RequiresAuthentication
    @PostMapping("/saveDelivery")
    public Result saveDelivery(@RequestBody DeliveryDTO deliveryDTO) throws Exception {

        deliveryService.saveDelivery(deliveryDTO);
        log.info("投递成功！");
        return Result.success();
    }

    @RequiresAuthentication
    @GetMapping("/getDelivery")
    public Result getDelivery()  {
        List<DeliveryVO> deliveryVOS = deliveryService.getDelivery();
        log.info("查询投递信息成功");
        return Result.success(deliveryVOS);
    }
}
