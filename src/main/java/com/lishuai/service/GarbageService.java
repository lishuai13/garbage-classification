package com.lishuai.service;

import com.lishuai.entity.Classification;
import com.lishuai.entity.Garbage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lishuai.entity.vo.ClassificationVO;

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
public interface GarbageService extends IService<Garbage> {

    /**
     * 垃圾分类查询
     * @param garbageName
     * @return
     */
    List<ClassificationVO> garbageClassify(String garbageName) throws IOException;

    /**
     * 获取垃圾分类信息
     * @return
     */
    List<Classification> classifyInfo();
}
