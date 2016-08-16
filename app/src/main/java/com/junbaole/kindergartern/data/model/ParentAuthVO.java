package com.junbaole.kindergartern.data.model;

/**
 * Created by TigerYao on 16/7/23.
 */
public class ParentAuthVO {
    public boolean as_auth = true;// (boolean, optional): 是否认证,
    public String child_name; //(string, optional): 宝贝姓名,
    public String name;// (string, optional): 家长姓名,
    public String other_rel;//(string, optional): 其他关系,
    public String phone_num; // (string, optional): 手机号码,
    public int rel_type;// (integer, optional): 与孩子关系,
    public int shcool_id;// (integer, optional): 学校Id,
    public int  v_code;// (integer, optional): 验证码,
    public int v_code_time = (int) (System.currentTimeMillis()/1000);// (integer, optional): 时间戳

}
