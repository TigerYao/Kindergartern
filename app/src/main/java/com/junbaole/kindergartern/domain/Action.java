package com.junbaole.kindergartern.domain;

import com.junbaole.kindergartern.data.model.BaseReponseModel;
import com.junbaole.kindergartern.data.model.SMSVO;
import com.junbaole.kindergartern.data.model.UserLoginVO;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by liangrenwang on 16/6/22.
 */
public interface Action {


    @POST("sms/send")
   Call<BaseReponseModel> sendPhoneNum(@Body SMSVO smsvo);
    @POST("sms/valid")
    Call<BaseReponseModel> checkVCode(@Body SMSVO smsvo);
    @POST("user/login")
    Call<BaseReponseModel> login(@Body UserLoginVO userLoginVO);


}
