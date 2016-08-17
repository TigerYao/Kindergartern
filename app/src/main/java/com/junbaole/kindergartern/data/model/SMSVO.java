package com.junbaole.kindergartern.data.model;

/**
 * Created by TigerYao on 16/6/30.
 */
public class SMSVO {

    public String phone_num;
    public int v_code;
    public int v_code_time = (int) (System.currentTimeMillis()/1000);
    public int v_type;
    public String wd;

}
