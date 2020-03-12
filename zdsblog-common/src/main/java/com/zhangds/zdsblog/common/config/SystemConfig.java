package com.zhangds.zdsblog.common.config;

/**
 * Create by zhangds
 * 2020-03-11 14:09
 **/
public class SystemConfig {

    private static String profile;

    private static boolean addressEnabled;

    public static String getProfile(){
        return profile;
    }

    public static void setProfile(String profile) {
        SystemConfig.profile = profile;
    }

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public static void setAddressEnabled(boolean addressEnabled) {
        SystemConfig.addressEnabled = addressEnabled;
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
