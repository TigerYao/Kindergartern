package com.junbaole.kindergartern.data.utils.qiniu;

import android.util.Log;

import com.junbaole.kindergartern.data.model.ImageInfo;
import com.junbaole.kindergartern.data.utils.event.UploadImgEvent;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liangrenwang on 16/6/1.
 */
public class QiNiuManager {

    private AtomicInteger atomicInteger;

    public static final class LazyManagerHolder {
        public static final QiNiuManager instance = new QiNiuManager();
    }

    Configuration configuration;

    public QiNiuManager() {
        configuration = new Configuration.Builder().connectTimeout(10).responseTimeout(60).build();
        atomicInteger = new AtomicInteger(0);
    }

    public static QiNiuManager getInstance() {
        return LazyManagerHolder.instance;
    }

    public void uploadSingleImg(ImageInfo imageInfo, UploadImgEvent event,int count) {
        uploadSingleImg(event, imageInfo.client_id, imageInfo.upload_path, imageInfo.auth);
        atomicInteger.set(count);
    }



    public void uploadSingleImg(final UploadImgEvent event, String imgPath, final String mkey, String token) {
        UploadManager uploadManager = new UploadManager(configuration);
        uploadManager.put(imgPath, mkey, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
               if(atomicInteger.decrementAndGet()==0){
                   EventBus.getDefault().post(event);
               }
                // res 包含hash、key等信息，具体字段取决于上传策略的设置。
                Log.i("qiniu", key + "ddd " + info + ",\r\n "
                        + res+"???"+mkey+"???"+event.lastId);
                // 七牛返回的文件名
            }
        }, null);

    }

}
