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

/**
 * Created by liangrenwang on 16/6/1.
 */
public class QiNiuManager {

    public static final class LazyManagerHolder {
        public static final QiNiuManager instance = new QiNiuManager();
    }

    Configuration configuration;

    public QiNiuManager() {
        configuration = new Configuration.Builder().connectTimeout(10).responseTimeout(60).build();

    }

    public static QiNiuManager getInstance() {
        return LazyManagerHolder.instance;
    }

    public void uploadSingleImg(ImageInfo imageInfo, UploadImgEvent event) {
        uploadSingleImg(event, imageInfo.client_id, imageInfo.image_id, imageInfo.auth);
    }



    public void uploadSingleImg(final UploadImgEvent event, String imgPath, final String mkey, String token) {
        UploadManager uploadManager = new UploadManager(configuration);
        uploadManager.put(imgPath, mkey, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
                // res 包含hash、key等信息，具体字段取决于上传策略的设置。
                Log.i("qiniu", key + "ddd " + info + ",\r\n "
                        + res+"???"+mkey+"???"+event.lastId);
                // 七牛返回的文件名
                if (mkey.equals(event.lastId)) {
                    EventBus.getDefault().post(event);
                }
            }
        }, null);

    }

}
