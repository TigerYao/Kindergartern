package com.junbaole.kindergartern.data.utils.qiniu;

import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;

/**
 * Created by liangrenwang on 16/6/1.
 */
public class QiNiuManager {

    UploadManager uploadManager;
    public QiNiuManager() {
        Configuration configuration = new Configuration.Builder().connectTimeout(10).responseTimeout(60).build();
        uploadManager = new UploadManager(configuration);
    }
}
