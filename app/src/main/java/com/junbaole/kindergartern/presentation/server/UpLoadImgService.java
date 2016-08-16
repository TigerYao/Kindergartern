package com.junbaole.kindergartern.presentation.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.junbaole.kindergartern.data.model.ImageInfo;
import com.junbaole.kindergartern.data.model.SendMessageInfo;
import com.junbaole.kindergartern.data.utils.qiniu.QiNiuManager;
import com.junbaole.kindergartern.domain.ActionManager;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class UpLoadImgService extends Service {

    private ArrayList<SendMessageInfo> messageQueues;

    public UpLoadImgService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        messageQueues = new ArrayList<>();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SendMessageInfo messageInfo =intent.getParcelableExtra("sendMessage");
        messageQueues.add(messageInfo);
        if(o==null||messageQueues.isEmpty()){
           uploardImg();
        }
        return super.onStartCommand(intent, flags, startId);
    }
    long  isCurrentId = -1;
    Observable o;
    private void uploardImg() {
         o= Observable.from(messageQueues);
        o.flatMap(new Func1<SendMessageInfo, Observable<ImageInfo>>() {
            @Override
            public Observable<ImageInfo> call(SendMessageInfo messageInfo) {

                return Observable.from(messageInfo.imageList);
            }

        }, new Func2<SendMessageInfo, ImageInfo, Object[]>() {
            @Override
            public Object[] call(SendMessageInfo sendMessageInfo, ImageInfo imageInfo) {
                try {
                    QiNiuManager.getInstance().uploadSingleImg(imageInfo.client_id,imageInfo.auth);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                messageQueues.remove(sendMessageInfo);
                return new Object[]{sendMessageInfo.id,sendMessageInfo.isDiray};
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<Object[]>() {
            @Override
            public void call(Object[] objes) {
                long id = (long) objes[0];
                boolean isDiary = (boolean) objes[1];
                if(id!=isCurrentId){
                    ActionManager.getSencondIntent(getApplicationContext()).confirm(isCurrentId,isDiary);
                    isCurrentId = id;
                }
            }
        });
    }
}
