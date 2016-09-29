package com.junbaole.kindergartern.domain;

import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.junbaole.kindergartern.data.model.BaseReponseModel;
import com.junbaole.kindergartern.data.model.CommentModel;
import com.junbaole.kindergartern.data.model.DiaryDetailInfo;
import com.junbaole.kindergartern.data.model.DiaryInfo;
import com.junbaole.kindergartern.data.model.ParentAuthVO;
import com.junbaole.kindergartern.data.model.SMSVO;
import com.junbaole.kindergartern.data.model.SendMessageInfo;
import com.junbaole.kindergartern.data.model.ShooleInfo;
import com.junbaole.kindergartern.data.model.UserInfo;
import com.junbaole.kindergartern.data.model.UserLoginVO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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
    Call<BaseReponseModel<JsonObject>> registerApp(@Body SMSVO smsvo);

    @PUT("user/chwd")
    Call<BaseReponseModel<JsonObject>> modifyPWD(@Body SMSVO smsvo);

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
    Call<BaseReponseModel<DiaryDetailInfo>> findMessage(@Path("moment_id") int moment_id);

    @POST("v1.2/diarys")
    Call<BaseReponseModel<SendMessageInfo>> sendDiarys(@Body SendMessageInfo sendMessageInfo);

    @GET("v1.2/diarys")
    Call<BaseReponseModel<DiaryInfo>> getDiarys(@Query("moment_type") ArrayList<String> momentList, @Query("user_id") String userId, @Query("page") int page, @Query("size") int size);

    @PUT("v1.2/diarys/confirm/{diary_id}")
    Call<BaseReponseModel<String>> confirmDiary(@Path("diary_id") long diaryID);

    @DELETE("v1.2/diarys/{diary_id}")
    Call<BaseReponseModel<JsonObject>> deletDiary(@Path("diary_id") int diaryId);

    @GET("v1.2/diarys/{diary_id}")
    Call<BaseReponseModel<DiaryDetailInfo>> queryDiary(@Path("diary_id") int diaryId);

    @PUT("v1.2/diarys/{diary_id}")
    Call<BaseReponseModel<JsonObject>> updateDiary(@Path("diary_id") int diaryId, @Body SendMessageInfo sendMessageInfo);

    @POST("v1.2/moments/{momentid}/favorities")
    Call<BaseReponseModel<JsonObject>> favorities(@Path("momentid") int momentid, @Query("userid") String userid, @Query("uuid") String uuid);

    @DELETE("v1.2/moments/{momentid}/favorities")
    Call<BaseReponseModel<String>> unFavorities(@Path("momentid") int momentid, @Query("userid") String userid);

    @Headers("Content-Type: application/json")
    @POST("v1.2/comments/")
    Call<BaseReponseModel<JsonObject>> judgeComment(@Body CommentModel commentModel);

    @DELETE("v1.2/comments/{comment_id}")
    Call<BaseReponseModel<JsonObject>> deleteJudgeComment(@Path("comment_id") int comment_id);

    @GET("user/rel/{userId}")
    Call<BaseReponseModel<ArrayList<UserInfo>>> getFriendsByUserId(@Path("userId") int userId);

    @POST("/user/addUserRel")
    Call<BaseReponseModel<JsonObject>> addFriend(@Body String params);

    @GET("user/{mobile}")
    Call<BaseReponseModel<UserInfo>>queryByPhone(@Path("mobile") String mobile);
}
