package com.junbaole.kindergartern.domain;

import com.junbaole.kindergartern.data.model.BaseReponseModel;
import com.junbaole.kindergartern.data.model.DiaryInfo;
import com.junbaole.kindergartern.data.model.ParentAuthVO;
import com.junbaole.kindergartern.data.model.SMSVO;
import com.junbaole.kindergartern.data.model.SendMessageInfo;
import com.junbaole.kindergartern.data.model.ShooleInfo;
import com.junbaole.kindergartern.data.model.UserInfo;
import com.junbaole.kindergartern.data.model.UserLoginVO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by liangrenwang on 16/6/22.
 */
public interface Action {

    @POST("sms/send")
    Call<BaseReponseModel<String>> sendPhoneNum(@Body SMSVO smsvo);

    @POST("sms/valid")
    Call<BaseReponseModel<String>> checkVCode(@Body SMSVO smsvo);

    @POST("user/login")
    Call<BaseReponseModel<UserInfo>> login(@Body UserLoginVO userLoginVO);

    @POST("user/reg")
    Call<BaseReponseModel<String>> registerApp(@Body SMSVO smsvo);

    @PUT("user/chwd")
    Call<BaseReponseModel<String>> modifyPWD(@Body SMSVO smsvo);

    @PUT("auth/parent")
    Call<BaseReponseModel<String>> parentAuth(@Body ParentAuthVO parentAuthVO);

    @GET("school/findByProvice")
    Call<BaseReponseModel<ArrayList<ShooleInfo>>> getShooleList(@Query("province") String proviceName);

    @GET("v1.2/moments/friendsCircle")
    Call<BaseReponseModel<DiaryInfo>> getFriendsCircle(@Query("moment_type") ArrayList<String> momentList, @Query("user_id") String userId, @Query("page") int page, @Query("size") int size);

    @POST("v1.2/moments")
    Call<BaseReponseModel<SendMessageInfo>> sendMessage(@Body SendMessageInfo messageInfo);

    @PUT("v1.2/moments/confirm/{moment_id}")
    Call<BaseReponseModel<String>> comfirm(@Path("moment_id") long moment_id);

    @DELETE("v1.2/moments/{moment_id}")
    Call<BaseReponseModel<String>> deleteMessage(@Path("moment_id") long moment_id);

    @GET("v1.2/moments/{moment_id}")
    Call<BaseReponseModel> findMessage(@Path("moment_id") long moment_id);

    @POST("v1.2/diarys")
    Call<BaseReponseModel<SendMessageInfo>> sendDiarys(@Body SendMessageInfo sendMessageInfo);

    @GET("v1.2/diarys")
    Call<BaseReponseModel<DiaryInfo>> getDiarys(@Query("moment_type") ArrayList<String> momentList, @Query("user_id") String userId, @Query("page") int page, @Query("size") int size);

    @PUT("v1.2/diarys/confirm/{diary_id}")
    Call<BaseReponseModel<String>> confirmDiary(@Path("diary_id") long diaryID);

    @DELETE("v1.2/diarys/{diary_id}")
    Call<BaseReponseModel<String>> deletDiary(@Path("diary_id") String diaryId);

    @GET("v1.2/diarys/{diary_id}")
    Call<BaseReponseModel<String>> queryDiary(@Path("diary_id") String diaryId);

    @PUT("v1.2/diarys/{diary_id}")
    Call<BaseReponseModel<String>> updateDiary(@Path("diary_id") String diaryId,@Body SendMessageInfo sendMessageInfo);


}
