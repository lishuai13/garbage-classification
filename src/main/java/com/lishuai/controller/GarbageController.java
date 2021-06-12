package com.lishuai.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lishuai.common.lang.Result;
import com.lishuai.entity.Classification;
import com.lishuai.entity.vo.ClassificationVO;
import com.lishuai.service.GarbageService;
import com.lishuai.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
@RequestMapping("/garbage")
public class GarbageController {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    GarbageService garbageService;

    @GetMapping("/test")
    public Result test(){
        return Result.success("进入方法成功");
    }



    @PostMapping("/classify")
    public Result classify(@NotNull(message = "输入不能为空") String garbageName) throws Exception {


        List<ClassificationVO> classificationVOS = (List<ClassificationVO>) redisUtil.get(garbageName);

        if (classificationVOS==null){
            classificationVOS = garbageService.garbageClassify(garbageName);
            if (classificationVOS.size()==0){
                throw new Exception("未查询到该垃圾");
            }else{
                redisUtil.set(garbageName,classificationVOS);
            }
        }
        log.info("垃圾查询成功！");
        return Result.success(classificationVOS);

        //todo @PathVariable 无法匹配中文问题，模糊查询索引问题,对于 %str% 这种形式的模糊查询，很难进行优化
    }

    @GetMapping("/classifyInfo")
    public Result classifyInfo() {

        List<Classification> classifications = garbageService.classifyInfo();
        log.info("获取垃圾分类信息成功！");
        return Result.success(classifications);
    }
}
