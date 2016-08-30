package com.junbaole.kindergartern.presentation.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.junbaole.kindergartern.data.model.SendMessageInfo;
import com.junbaole.kindergartern.data.utils.event.SendMsgEvent;
import com.junbaole.kindergartern.data.utils.event.UploadImgEvent;
import com.junbaole.kindergartern.data.utils.qiniu.QiNiuManager;
import com.junbaole.kindergartern.domain.ActionManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class UpLoadImgService extends Service {

    private Queue<SendMessageInfo> messageQueues;
    private AtomicBoolean isUploading;

    public UpLoadImgService() {}

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        messageQueues = new LinkedList<>();
        EventBus.getDefault().register(this);
        isUploading = new AtomicBoolean(false);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Subscribe
    public void uploadImg(SendMsgEvent event) {
        SendMessageInfo messageInfo = event.sendMessageInfo;
        if (messageInfo.imageList == null || messageInfo.imageList.size() == 0) {
            ActionManager.getSencondIntent(getApplicationContext()).confirm(messageInfo.id, messageInfo.isDiray);
        } else {
            messageQueues.add(messageInfo);
            if(isUploading.compareAndSet(false,true)){
                uploardImg();
            }
        }
    }

    @Subscribe
    public void handleMessage(UploadImgEvent msg) {
        ActionManager.getSencondIntent(getApplicationContext()).confirm(msg.messageId, msg.isDiary);
        uploardImg();
    }

    private void uploardImg() {
        SendMessageInfo sendMessageInfo = messageQueues.poll();
        if(sendMessageInfo!=null) {
            UploadImgEvent uploadImgEvent = new UploadImgEvent();
            uploadImgEvent.messageId = sendMessageInfo.id;
            uploadImgEvent.lastId = sendMessageInfo.getLastImgId();
            uploadImgEvent.isDiary = sendMessageInfo.isDiray;
            for (int i = 0; i < sendMessageInfo.imageList.size(); i++) {
                QiNiuManager.getInstance().uploadSingleImg(sendMessageInfo.imageList.get(i), uploadImgEvent,sendMessageInfo.imageList.size());
            }
        }else{
            isUploading.compareAndSet(true,false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
