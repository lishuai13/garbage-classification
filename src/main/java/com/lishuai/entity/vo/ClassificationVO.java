package com.lishuai.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户查询垃圾返回的结果
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */

@Data
public class ClassificationVO implements Serializable {

    /**
     * 垃圾名
     */
    private String garbageName;

    /**
     * 垃圾分类名称
     */
    private String categoryName;

    /**
     * 垃圾分类描述
     */
    private String categoryDescription;

    /**
     * 垃圾分类图片
     */
    private String categoryImage;
}
