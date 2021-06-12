package com.lishuai.controller;


import com.lishuai.common.lang.Result;
import com.lishuai.entity.dto.ComplaintDTO;
import com.lishuai.entity.vo.ComplaintVO;
import com.lishuai.service.ComplaintService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    ComplaintService complaintService;

    @RequiresAuthentication
    @PostMapping("/saveComplaint")
    public Result saveComplaint(@RequestBody @Validated ComplaintDTO complaintDTO){
        complaintService.saveComplaint(complaintDTO);
        log.info("新增投诉成功！");
        return Result.success();
    }

    @RequiresAuthentication
    @GetMapping("/getComplaint")
    public Result getComplaint(){
        List<ComplaintVO> complaintVOS = complaintService.getComplaint();
        log.info("查询用户投诉成功！");
        return Result.success(complaintVOS);
    }
}
