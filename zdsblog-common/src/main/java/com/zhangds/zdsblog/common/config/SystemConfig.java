package com.zhangds.zdsblog.common.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Create by zhangds
 * 2020-03-11 14:09
 **/
public class SystemConfig {

    private static String profile;

    public static String getProfile(){
        return profile;
    }

    public static void setProfile(String profile) {
        SystemConfig.profile = profile;
    }

    /**
     * 获取头像上传路径
     */
    public static String getIconPath()
    {
        return getProfile() + "/icon";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
        return getProfile() + "/download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath()
    {
        return getProfile() + "/upload";
    }
}
