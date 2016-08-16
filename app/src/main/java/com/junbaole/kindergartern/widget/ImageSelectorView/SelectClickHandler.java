package com.junbaole.kindergartern.widget.ImageSelectorView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.junbaole.kindergartern.data.model.ImageInfo;
import com.junbaole.kindergartern.data.model.SendMessageInfo;
import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseTitleClickHandler;
import com.junbaole.kindergartern.presentation.send.DisplayImgsActivity;
import com.junbaole.kindergartern.presentation.send.SendActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TigerYao on 16/7/23.
 */
public class SelectClickHandler extends BaseTitleClickHandler {
    public SelectClickHandler(BaseActivity mActivity) {
        super(mActivity);
    }

    public void onClickScanPics(View view) {
        ((ImageSelectorActivity) mActivity).scranPics(0);
    }

    public void onClickSure(View view) {
        ArrayList<ImageInfo> datas = ((ImageSelectorActivity) mActivity).mAdapter.mSelectedImage;
        if(datas!=null&&datas.size()>0){
            Intent intent = new Intent(mActivity, SendActivity.class);
            mActivity.startActivity(intent);
            EventBus.getDefault().post(datas);
            mActivity.finish();
        }
    }

    @Override
    public void onClickForgetPsw(View view) {
        SkipActivityUtils.specialFinish(mActivity);
    }
}
