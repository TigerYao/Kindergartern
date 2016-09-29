package com.junbaole.kindergartern.data.utils.chatutil;

import java.util.ArrayList;
import java.util.List;

import org.greenrobot.eventbus.EventBus;

import com.tencent.TIMAddFriendRequest;
import com.tencent.TIMCallBack;
import com.tencent.TIMConversation;
import com.tencent.TIMConversationType;
import com.tencent.TIMFaceElem;
import com.tencent.TIMFriendResult;
import com.tencent.TIMFriendshipManager;
import com.tencent.TIMImageElem;
import com.tencent.TIMLocationElem;
import com.tencent.TIMManager;
import com.tencent.TIMMessage;
import com.tencent.TIMMessageListener;
import com.tencent.TIMSoundElem;
import com.tencent.TIMTextElem;
import com.tencent.TIMUser;
import com.tencent.TIMUserProfile;
import com.tencent.TIMValueCallBack;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by yaohu on 16/9/26.
 */

public class ChatUtil implements TIMMessageListener{
    private static TIMManager timManager;
    final static int SDKAPPID = 1400013233;
    final static String accountType = "6803";
    private static TIMConversation conversation;
    private static Context mCtx;
    static ChatUtil chatUtil;
    public static void initTIM(Context ctx){
        mCtx = ctx;
        chatUtil = new ChatUtil();
        timManager = TIMManager.getInstance();
        timManager.init(mCtx);
        timManager.initLogSettings(true,"/mnt/sdcard/kinderlog/");
        timManager.setLogPrintEanble(true);
        timManager.disableCrashReport();
    }

    public static void loginQCloud(String identifier, String userSig){
        if(timManager==null){
            initTIM(mCtx);
        }
        TIMUser user = new TIMUser();
        user.setAccountType(accountType);
        user.setAppIdAt3rd(SDKAPPID+"");
        user.setIdentifier(identifier);
        timManager.login(SDKAPPID, user, userSig, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                Toast.makeText(mCtx,s,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess() {
                EventBus.getDefault().post(new IMLoginEvent());
            }
        });
        timManager.initStorage(SDKAPPID, user, userSig, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                Toast.makeText(mCtx,s,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess() {

            }
        });
    }

    public static void createSingleConvertsation(String friendUsersig){
        conversation = timManager.getConversation(TIMConversationType.C2C,friendUsersig);
    }

    public static void createGroupConvertsation(String groupId){
        conversation = timManager.getConversation(TIMConversationType.Group,groupId);
    }

    public static void sendMessage(String msg){
        TIMMessage message = new TIMMessage();
        TIMTextElem elem = new TIMTextElem();
        elem.setText(msg);
        if(message.addElement(elem)!=0){
            Log.d("add", "addElement failed");
            return;
        }
        conversation.sendMessage(message, new TIMValueCallBack<TIMMessage>() {
            @Override
            public void onError(int i, String s) {
                Toast.makeText(mCtx,s,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(TIMMessage timMessage) {
                EventBus.getDefault().post(timMessage);
            }
        });
    }

    public static void sendImgMsg(String path){
        TIMMessage message = new TIMMessage();
        TIMImageElem elem = new TIMImageElem();
        elem.setPath(path);
        if(message.addElement(elem)!=0){
            Log.d("add", "addElement failed");
            return;
        }
        conversation.sendMessage(message, new TIMValueCallBack<TIMMessage>() {
            @Override
            public void onError(int i, String s) {
                Toast.makeText(mCtx,s,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(TIMMessage timMessage) {
                EventBus.getDefault().post(timMessage);
            }
        });
    }
    public static void sendEmojiMsg(String path,byte[]array,int index){
        TIMMessage message = new TIMMessage();
        TIMFaceElem elem = new TIMFaceElem();
        elem.setData(array);
        elem.setIndex(index);
        if(message.addElement(elem)!=0){
            Toast.makeText(mCtx,"发送失败,稍后重试",Toast.LENGTH_LONG).show();
            return;
        }
        conversation.sendMessage(message, new TIMValueCallBack<TIMMessage>() {
            @Override
            public void onError(int i, String s) {
                Toast.makeText(mCtx,"发送失败,稍后重试",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(TIMMessage timMessage) {
                EventBus.getDefault().post(timMessage);
            }
        });
    }
    public static void sendVoiceMsg(String path,long duration){
        TIMMessage message = new TIMMessage();
        TIMSoundElem elem = new TIMSoundElem();
        elem.setPath(path);
        elem.setDuration(duration);
        if(message.addElement(elem)!=0){
            Toast.makeText(mCtx,"发送失败,稍后重试",Toast.LENGTH_LONG).show();
            return;
        }
        conversation.sendMessage(message, new TIMValueCallBack<TIMMessage>() {
            @Override
            public void onError(int i, String s) {
                Toast.makeText(mCtx,"发送失败,稍后重试",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(TIMMessage timMessage) {
                EventBus.getDefault().post(timMessage);
            }
        });
    }

    public static void sendLocationMsg(String locationName,double lat,double lon){
        TIMMessage message = new TIMMessage();
        //添加位置信息
        TIMLocationElem elem = new TIMLocationElem();
        elem.setLatitude(lat);   //设置纬度
        elem.setLongitude(lon);   //设置经度
        elem.setDesc(locationName);
        if(message.addElement(elem)!=0){
            Toast.makeText(mCtx,"发送失败,稍后重试",Toast.LENGTH_LONG).show();
            return;
        }
        conversation.sendMessage(message, new TIMValueCallBack<TIMMessage>() {
            @Override
            public void onError(int i, String s) {
                Toast.makeText(mCtx,"发送失败,稍后重试",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(TIMMessage timMessage) {
                EventBus.getDefault().post(timMessage);
            }
        });
    }


    public static TIMAddFriendRequest createFriend(String addrSource,String identifier, String message, String mark){
        TIMAddFriendRequest req = new TIMAddFriendRequest();
        req.setAddrSource(addrSource);
        req.setAddWording(message);
        req.setIdentifier(identifier);
        req.setRemark(mark);
        return req;
    }

    public static void addFriend(String identifier, String message, String mark){
        List<TIMAddFriendRequest> requests = new ArrayList<>();
        requests.add(createFriend("AddSource_Type_Android",identifier,message,mark));
        addFriends(requests);
    }
    public static void addFriends(List<TIMAddFriendRequest> requests){
        TIMFriendshipManager.getInstance().addFriend(requests, new TIMValueCallBack<List<TIMFriendResult>>() {
            @Override
            public void onError(int i, String s) {
                Log.e("chatutil", "addFriend failed: " + i + " desc");
            }

            @Override
            public void onSuccess(List<TIMFriendResult> timFriendResults) {
                EventBus.getDefault().post(timFriendResults);
            }
        });
    }

    public void getFriendList(){
        //获取好友列表
        TIMFriendshipManager.getInstance().getFriendList(new TIMValueCallBack<List<TIMUserProfile>>(){
            @Override
            public void onError(int code, String desc){
                //错误码code和错误描述desc，可用于定位请求失败原因
                //错误码code列表请参见错误码表
                Log.e("chatutil", "getFriendList failed: " + code + " desc");
            }

            @Override
            public void onSuccess(List<TIMUserProfile> result){
                for(TIMUserProfile res : result){
                    Log.e("chatutil", "identifier: " + res.getIdentifier() + " nickName: " + res.getNickName()
                            + " remark: " + res.getRemark());
                }
            }
        });
    }

    public static void addMessageListener(){
        timManager.addMessageListener(new TIMMessageListener() {
            @Override
            public boolean onNewMessages(List<TIMMessage> list) {
                return false;
            }
        });
    }

    public static void addListeners(){
       timManager.addMessageListener(chatUtil);
    }

    @Override
    public boolean onNewMessages(List<TIMMessage> list) {
        return false;
    }
}
