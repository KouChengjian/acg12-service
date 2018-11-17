package com.acg12.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

public class PropUitl {

    public final static Props getProps(){
        Props activeStatus = new Props("sy.config.active.properties");
        String status = activeStatus.getStr("active");
        Props props = new Props(StrUtil.format("sy.config.{}.properties",status));
        props.setProperty("env_active", status);
        return props;
    }

}
