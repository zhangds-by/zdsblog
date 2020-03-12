package com.zhangds.zdsblog.common.model.constants;

/**
 * Create by zhangds
 * 2020-03-04 18:14
 **/
public interface SysContants {

    String BASE_URL = "/blog";

    //图片验证码
    String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";

    // 返回报文头 json格式，编码 utf-8
    String JSON_UTF8 = "application/json;charset=utf-8";
    // 返回 html
    String HTML_UTF8 = "text/html;charset=utf-8";
    // 用户注册 URL
    String REGIST_URL = "/index/regist";
    // 登录处理接口
    String LOGIN_URL = "/index/login";
    // 资源映射路径 前缀
    public static final String RESOURCE_PREFIX = "/profile";

    static final String REGION = "内网IP|内网IP";

}
