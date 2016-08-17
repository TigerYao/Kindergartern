package com.junbaole.kindergartern.data.utils;


/**
 * 描述：常量.
 */
public class ConstantUtils {
    /**
     * 调试模式为true  上线模式为false
     */
    public static final boolean DEBUG = true;
    //测试环境
    public static final String BASEURL = DEBUG ? "http://192.168.0.98:901/Api/getway" : "https://getway.liangren.com/Api/getway";

    //外网访问测试环境
//    public static final String BASEURL = DEBUG ? "http://106.2.182.130:8077/Api/getway" : "https://getway.liangren.com/Api/getway";
//    public static final String BASEURL = DEBUG ? "http://106.2.182.130:8078/Api/getway" : "https://getway.liangren.com/Api/getway";//测试2
    //Beta环境
//    public static final String BASEURL = DEBUG ? "https://betagetway.liangren.com/Api/getway" : "https://getway.liangren.com/Api/getway";


//    public static final String BASEURL_H5 = DEBUG ?"":"http://static.liangren.com/b2b";
    public static final String PATCH_INIT_VERSION = "1.0.0";
    public static final String API_VERSION = "1";
    public static final String YES = "YES";
    public static final String NO = "NO";
    public static final String ON = "ON";
    public static final String OFF = "OFF";
    public static final String SALT = "e1f223373ffe55dc18b2e788b77459eb52767021";
    public static final String UPDATE_TYPE_FORCE = "FORCE";//强制
    public static final String UPDATE_TYPE_TIPS = "TIPS";//非强制
    public static final String UMENG_APP_KEY="56e3ba9967e58e105c000550";
    public static final String APP_ID = "wx9918e0a6fdc1b357";
    public static final String APPSECRET = "b83ba7ea98fbad79bec2973b868d95e7";
    //
    public static final int PLEASE_ADD_ADDRESS=6056;//请添加收货地址
    public static final int PLEASE_COMPLEMENTED_ADDRESS=12005;//请补全收货地址

}
