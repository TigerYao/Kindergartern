package com.junbaole.kindergartern.presentation.send;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.amap.api.services.core.PoiItem;
import com.junbaole.kindergartern.data.model.SendMessageInfo;
import com.junbaole.kindergartern.data.utils.MapUtils;
import com.junbaole.kindergartern.data.utils.activity.AppInfo;
import com.junbaole.kindergartern.data.utils.amaputils.AmapQueryUtils;
import com.junbaole.kindergartern.presentation.adapter.ShooleListAdapter;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseTitleClickHandler;

import java.util.ArrayList;

/**
 * Created by yaohu on 16/7/22.
 */
public class SendClickHandler extends BaseTitleClickHandler{

    public SendClickHandler(BaseActivity mActivity) {
        super(mActivity);
    }

    public void onClickLocation(View view){
        new AmapQueryUtils().queryKeyWords(mActivity);
    }

    public void onClickProtect(View view){

    }

    @Override
    public void onClickLeftText(View view) {
        mActivity.finish();
    }

    @Override
    public void onClickForgetPsw(View view) {
        SendMessageInfo sendMessageInfo = ((SendActivity)mActivity).getMessageInfo();
        sendMessageInfo.images.remove(null);
        mActivity.secondActionManager.sendMessage(sendMessageInfo,false);
    }

}
