package com.junbaole.kindergartern.presentation.base;

import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;

import android.view.View;

/**
 * Created by liangrenwang on 16/6/7.
 */
public class BaseTitleClickHandler {

    protected BaseActivity mActivity;

    public BaseTitleClickHandler(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void onClickReturn(View view) {
        SkipActivityUtils.specialFinish(mActivity);
    }

    public void onClickForgetPsw(View view) {

    }

    public void onClickRightImg(View view) {

    }

    public void onClickLeftText(View view) {}

}
