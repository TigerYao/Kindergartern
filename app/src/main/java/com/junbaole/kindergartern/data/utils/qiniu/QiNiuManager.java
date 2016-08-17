package com.junbaole.kindergartern.data.utils.qiniu;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liangrenwang on 16/6/1.
 */
public class QiNiuManager {
    private Lock lock = new ReentrantLock();// 锁对象
    boolean isOk;

    public static final class LazyManagerHolder {
        public static final QiNiuManager instance = new QiNiuManager();
    }

    UploadManager uploadManager;

    private QiNiuManager() {
        Configuration configuration = new Configuration.Builder().connectTimeout(10).responseTimeout(60).build();
        uploadManager = new UploadManager(configuration);
    }

    public static QiNiuManager getInstance() {
        return LazyManagerHolder.instance;
    }


    public boolean uploadSingleImg(String imgPath, String token) throws InterruptedException {

        synchronized (this) {
            lock.lock();
            uploadManager.put(imgPath, null, token, new UpCompletionHandler() {
                @Override
                public void complete(String key, ResponseInfo info, JSONObject response) {
                    isOk = info.isOK();
                    lock.unlock();
                }
            }, null);
        }
        return isOk;
    }

    private void uploadImg() {
    }

    private String getToken() {

        return "yaohu";
    }
}
