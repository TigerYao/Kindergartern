package com.junbaole.kindergartern.presentation.send;

import android.app.Activity;
import android.view.View;

import com.junbaole.kindergartern.data.model.SendMessageInfo;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseTitleClickHandler;

/**
 * Created by yaohu on 16/7/22.
 */
public class SendClickHandler extends BaseTitleClickHandler{

    public SendClickHandler(BaseActivity mActivity) {
        super(mActivity);
    }

    public void onClickLocation(View view){

    }

    public void onClickProtect(View view){

    }

    @Override
    public void onClickForgetPsw(View view) {
        SendMessageInfo sendMessageInfo = ((SendActivity)mActivity).getMessageInfo();
        mActivity.secondActionManager.sendMessage(sendMessageInfo,false);
    }
}
