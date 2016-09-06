package com.junbaole.kindergartern.presentation.send;

import com.junbaole.kindergartern.data.model.SendMessageInfo;
import com.junbaole.kindergartern.data.utils.amaputils.AmapQueryUtils;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseTitleClickHandler;

import android.view.View;

/**
 * Created by yaohu on 16/7/22.
 */
public class SendClickHandler extends BaseTitleClickHandler {

    public SendClickHandler(BaseActivity mActivity) {
        super(mActivity);
    }

    public void onClickLocation(View view) {
        new AmapQueryUtils().queryKeyWords(mActivity);
    }

    public void onClickProtect(View view) {

    }

    @Override
    public void onClickLeftText(View view) {
        mActivity.finish();
    }

    @Override
    public void onClickForgetPsw(View view) {
        SendMessageInfo sendMessageInfo = ((SendActivity)mActivity).getMessageInfo();
        sendMessageInfo.images.remove(null);
        if (sendMessageInfo.id > 0&&sendMessageInfo.isDiray) {
            mActivity.secondActionManager.updateDiary((int)sendMessageInfo.id,sendMessageInfo);
        } else
            mActivity.secondActionManager.sendMessage(sendMessageInfo, sendMessageInfo.isDiray);
    }

}
