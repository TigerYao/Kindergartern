package com.junbaole.kindergartern.presentation.personal;

import android.content.Intent;
import android.view.View;

import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseTitleClickHandler;

/**
 * Created by yaohu on 2016/10/25.
 */

public class BabyClickHandler{

    public void onClickOpenCamera(View view){
        view.getContext().startActivity(new Intent(view.getContext(),AddBabyActivity.class));
    }

    public void onClickAddBaby(View view){
        view.getContext().startActivity(new Intent(view.getContext(),AddBabyActivity.class));
    }
}
