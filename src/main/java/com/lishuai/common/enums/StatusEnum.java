package com.lishuai.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * 数据库用户状态映射
 * @author lishuai
 */
public enum StatusEnum {

    NORMAL(1,true),
    BAN(0,false);

    /**
     * 枚举映射的键
     */
    @EnumValue
    private int code;
    private Boolean status;

    StatusEnum(int code,Boolean status){
        this.code=code;
        this.status=status;
    }

    public int getCode() {
        return code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}