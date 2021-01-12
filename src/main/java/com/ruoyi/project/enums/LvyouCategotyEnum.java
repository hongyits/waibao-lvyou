package com.ruoyi.project.enums;

import lombok.Data;

public enum LvyouCategotyEnum {

    INFO("info","旅游信息"),
    JIPIAO("jipiao","机票信息"),
    ZUCHE("zuche","马尼拉租车");

    private String code;
    private String name;


    LvyouCategotyEnum(String code,String name){
        this.code = code;
        this.name = name;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
