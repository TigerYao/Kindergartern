package com.junbaole.kindergartern.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.greenrobot.eventbus.EventBus;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonObject;
import com.junbaole.kindergartern.data.model.BaseReponseModel;
import com.junbaole.kindergartern.data.model.CommentModel;
import com.junbaole.kindergartern.data.model.DiaryDetailInfo;
import com.junbaole.kindergartern.data.model.DiaryInfo;
import com.junbaole.kindergartern.data.model.LevelHistoryModel;
import com.junbaole.kindergartern.data.model.NewFriendModle;
import com.junbaole.kindergartern.data.model.ParentAuthVO;
import com.junbaole.kindergartern.data.model.PersonalLevelModel;
import com.junbaole.kindergartern.data.model.SMSVO;
import com.junbaole.kindergartern.data.model.SendMessageInfo;
import com.junbaole.kindergartern.data.model.ShooleInfo;
import com.junbaole.kindergartern.data.model.UserInfo;
import com.junbaole.kindergartern.data.model.UserLoginVO;
import com.junbaole.kindergartern.data.utils.JsonUtils;
import com.junbaole.kindergartern.data.utils.SharedPreferenceUtil;
import com.junbaole.kindergartern.data.utils.chatutil.ChatUtil;
import com.junbaole.kindergartern.data.utils.event.CofirmFriendShipEvent;
import com.junbaole.kindergartern.data.utils.event.CommentEvent;
import com.junbaole.kindergartern.data.utils.event.DataRefreshEvent;
import com.junbaole.kindergartern.data.utils.event.DiaryEvent;
import com.junbaole.kindergartern.data.utils.event.UserInfoEvent;
import com.junbaole.kindergartern.network.RetrofitServer;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by TigerYao on 16/7/21.
 */
public class ActionManager {

    Action action, secondAction;
    static Context mCtx;

    private static class SingletonHolder {
        private final static ActionManager INSTANCE = new ActionManager();
    }

    private ActionManager() {
        action = RetrofitServer.getInstance().createService(Action.class);
    }

    private ActionManager(boolean seconde) {
        secondAction = RetrofitServer.getInstanceSecond().createServiceSecond(Action.class);
    }

    public static ActionManager getInstance(Context ctx) {
        mCtx = ctx;
        return SingletonHolder.INSTANCE;
    }

    private static ActionManager instance;

    public static ActionManager getSencondIntent(Context ctx) {
        if (instance == null) {
            mCtx = ctx;
            instance = new ActionManager(true);
        }

        return instance;
    }

    public void sendPhoneNum(final String phoneNum) {
        final SMSVO smsvo = new SMSVO();
        smsvo.phone_num = phoneNum;
        final SendPhoneEvent sendPhoneEvent = new SendPhoneEvent();
        sendPhoneEvent.type = 1;
        action.sendPhoneNum(smsvo).enqueue(new CallBackListener<String>() {
            @Override
            public void onSuccess(String s) {
                sendPhoneEvent.successMsg = phoneNum;
                sendPhoneEvent.time = smsvo.v_code_time;
                sendPhoneEvent.isSuccess = true;
                EventBus.getDefault().post(sendPhoneEvent);
            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void checkCode(final String phoneNum, final int checkCode, final int time) {
        SMSVO smsvo = new SMSVO();
        smsvo.phone_num = phoneNum;
        smsvo.v_code_time = time;
        smsvo.v_code = checkCode;
        final SendPhoneEvent sendPhoneEvent = new SendPhoneEvent();
        sendPhoneEvent.type = 2;
        action.checkVCode(smsvo).enqueue(new CallBackListener<String>() {
            @Override
            public void onSuccess(String s) {
                sendPhoneEvent.successMsg = checkCode + "";
                sendPhoneEvent.time = time;
                sendPhoneEvent.isSuccess = true;
                EventBus.getDefault().post(sendPhoneEvent);
            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void registerApp(final String phoneNum, int checkCode, String psw, int type, int time) {
        SMSVO smsvo = new SMSVO();
        smsvo.phone_num = phoneNum;
        smsvo.v_code = checkCode;
        smsvo.wd = psw;
        smsvo.v_code_time = time;
        final SendPhoneEvent sendPhoneEvent = new SendPhoneEvent();
        sendPhoneEvent.type = 3;

        CallBackListener<JsonObject> callBackListener = new CallBackListener<JsonObject>() {
            @Override
            public void onSuccess(JsonObject s) {
                sendPhoneEvent.successMsg = phoneNum;
                EventBus.getDefault().post(sendPhoneEvent);
            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        };
        if (type == 1) {
            action.modifyPWD(smsvo).enqueue(callBackListener);
        } else
            action.registerApp(smsvo).enqueue(callBackListener);

    }

    public void loginApp(final UserLoginVO loginVO) {
        final SendPhoneEvent sendPhoneEvent = new SendPhoneEvent();
        sendPhoneEvent.type = 4;
        action.login(loginVO).enqueue(new CallBackListener<UserInfo>() {
            @Override
            public void onSuccess(UserInfo data) {
                sendPhoneEvent.successMsg = loginVO.phone_num;
                sendPhoneEvent.isSuccess = true;
                sendPhoneEvent.obj = data;
                SharedPreferenceUtil.putUserInfo(mCtx, data);
                if (data.asValid) {
                    new MaterialDialog.Builder(mCtx).content("你已经与宝贝的老师成为好友了，现在让我们一起关注宝贝在幼儿园的表现吧").show();
                    // sendPhoneEvent.type = 6;
                }
                ChatUtil.loginQCloud(data.phoneNum, data.token);
                EventBus.getDefault().post(sendPhoneEvent);
            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void parentAuth(final ParentAuthVO parentAuthVO) {
        final SendPhoneEvent sendPhoneEvent = new SendPhoneEvent();
        sendPhoneEvent.type = 7;
        action.parentAuth(parentAuthVO).enqueue(new CallBackListener<String>() {
            @Override
            public void onSuccess(String s) {
                sendPhoneEvent.isSuccess = true;
                sendPhoneEvent.successMsg = parentAuthVO.phone_num;
                EventBus.getDefault().post(sendPhoneEvent);
            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getShoolList(String cityName) {
        action.getShooleList(cityName).enqueue(new CallBackListener<ArrayList<ShooleInfo>>() {
            @Override
            public void onSuccess(ArrayList<ShooleInfo> arrayList) {
                EventBus.getDefault().post(arrayList);
            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void sendMessage(final SendMessageInfo sendMessageInfo, final boolean isDiary) {
        CallBackListener callBackListener = new CallBackListener<SendMessageInfo>() {
            @Override
            public void onSuccess(SendMessageInfo messageInfo) {
                messageInfo.isDiray = isDiary;
                EventBus.getDefault().post(messageInfo);
            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        };
        if (isDiary) {
            secondAction.sendDiarys(sendMessageInfo).enqueue(callBackListener);
        } else
            secondAction.sendMessage(sendMessageInfo).enqueue(callBackListener);
    }

    public void getCommonts(int userId, int page, final boolean isDiary) {
        CallBackListener callBackListener = new CallBackListener<DiaryInfo>() {
            @Override
            public void onSuccess(DiaryInfo s) {
                EventBus.getDefault().post(new DiaryEvent(true, isDiary, s));
            }

            @Override
            public void onFail(String failReason) {
                EventBus.getDefault().post(new DiaryEvent(false));
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        };
        if (isDiary) {
            secondAction.getDiarys(null, userId + "", page, 10).enqueue(callBackListener);
        } else
            secondAction.getFriendsCircle(null, userId + "", page, 10).enqueue(callBackListener);
    }

    public void deleteDiary(int diaryId) {
        secondAction.deletDiary(diaryId).enqueue(new CallBackListener<JsonObject>() {
            @Override
            public void onSuccess(JsonObject s) {
                ((Activity)mCtx).finish();
            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void queyDiary(int diaryId, boolean isDiary) {
        CallBackListener callBackListener = new CallBackListener<DiaryDetailInfo>() {
            @Override
            public void onSuccess(DiaryDetailInfo s) {
                // ToDo
                EventBus.getDefault().post(new CommentEvent());
            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        };
        if (isDiary)
            secondAction.queryDiary(diaryId).enqueue(callBackListener);
        else
            secondAction.findMessage(diaryId).enqueue(callBackListener);
    }

    public void updateDiary(int diaryId, SendMessageInfo sendMessageInfo) {
        secondAction.updateDiary(diaryId, sendMessageInfo).enqueue(new CallBackListener<JsonObject>() {
            @Override
            public void onSuccess(JsonObject s) {

            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void confirm(long id, boolean isDiary) {
        CallBackListener<String> callBackListener = new CallBackListener<String>() {
            @Override
            public void onSuccess(String s) {
                EventBus.getDefault().post(new DataRefreshEvent());
            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        };
        if (isDiary) {
            secondAction.confirmDiary(id).enqueue(callBackListener);
        } else
            secondAction.comfirm(id).enqueue(callBackListener);
    }

    public void favorite(int commentId, String userid, String uuid) {
        secondAction.favorities(commentId, userid, uuid).enqueue(new CallBackListener<JsonObject>() {
            @Override
            public void onSuccess(JsonObject s) {
                Toast.makeText(mCtx, "点赞成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void unFavorite(int commentId, String userId) {
        secondAction.unFavorities(commentId, userId).enqueue(new CallBackListener<String>() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void judge(final CommentModel commentModel, final boolean isDiary) {
        secondAction.judgeComment(commentModel).enqueue(new CallBackListener<JsonObject>() {
            @Override
            public void onSuccess(JsonObject jsonObject) {
                queyDiary(commentModel.moment_id, isDiary);
            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void deleteJudge(int commentid) {
        secondAction.deleteJudgeComment(commentid).enqueue(new CallBackListener<JsonObject>() {
            @Override
            public void onSuccess(JsonObject jsonObject) {

            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getFriendsList(int userid) {
        action.getFriendsByUserId(userid).enqueue(new CallBackListener<ArrayList<UserInfo>>() {
            @Override
            public void onSuccess(ArrayList<UserInfo> userInfo) {
                if (userInfo != null)
                    EventBus.getDefault().post(userInfo);
            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addFriend(long userId, long otherUserid) {
        Map<String, Long> map = new HashMap();
        map.put("userId", userId);
        map.put("otherUserId", otherUserid);
        action.addFriend(map).enqueue(new CallBackListener<JsonObject>() {
            @Override
            public void onSuccess(JsonObject jsonObject) {
                Toast.makeText(mCtx, "请求发送成功", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void queryByPhone(String phoneNum) {
        action.queryByPhone(phoneNum).enqueue(new CallBackListener<UserInfo>() {
            @Override
            public void onSuccess(UserInfo info) {
                if (info != null) {
                    UserInfoEvent userInfoEvent = new UserInfoEvent();
                    userInfoEvent.userInfo = info;
                    EventBus.getDefault().post(userInfoEvent);
                } else
                    onFail("没有数据");

            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getNewFriendsList() {
        action.getNewFriends(SharedPreferenceUtil.getUserInfo(mCtx).id).enqueue(new CallBackListener<NewFriendModle>() {
            @Override
            public void onSuccess(NewFriendModle newFriendInfos) {
                EventBus.getDefault().post(newFriendInfos);
            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void acceptShip(long friendId) {
        Map<String, Long> queryMap = new HashMap<>();
        queryMap.put("userId", (long)SharedPreferenceUtil.getUserInfo(mCtx).id);
        queryMap.put("otherUserId", friendId);
        action.confirmRelationship(queryMap).enqueue(new CallBackListener<String>() {
            @Override
            public void onSuccess(String stringBaseReponseModel) {
//                if (Boolean.getBoolean(JsonUtils.getString(stringBaseReponseModel,"data","false")))
                    EventBus.getDefault().post(new CofirmFriendShipEvent());
//                else
//                    onFail("确认失败,稍后重试!");
            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getLevels(){
        action.getMyLevel(SharedPreferenceUtil.getUserInfo(mCtx).id).enqueue(new CallBackListener<PersonalLevelModel>() {
            @Override
            public void onSuccess(PersonalLevelModel personalLevelModel) {
                EventBus.getDefault().post(personalLevelModel);
            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getLevelHistory(){
        action.getLevelHistory(SharedPreferenceUtil.getUserInfo(mCtx).id).enqueue(new CallBackListener<LevelHistoryModel>() {
            @Override
            public void onSuccess(LevelHistoryModel levelHistoryModel) {
                EventBus.getDefault().post(levelHistoryModel);
            }

            @Override
            public void onFail(String failReason) {
                Toast.makeText(mCtx, failReason, Toast.LENGTH_LONG).show();
            }
        });
    }
}
