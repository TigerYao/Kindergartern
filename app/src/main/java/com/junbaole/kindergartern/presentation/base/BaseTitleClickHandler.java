package com.junbaole.kindergartern.presentation.base;

import android.app.Activity;
import android.view.View;

import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;

/**
 * Created by liangrenwang on 16/6/7.
 */
public class BaseTitleClickHandler {

    protected Activity mActivity;

    public BaseTitleClickHandler(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void onClickReturn(View view) {
        SkipActivityUtils.specialFinish(mActivity);
    }

    public void onClickForgetPsw(View view) {

    }

}
