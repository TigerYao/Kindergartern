package com.junbaole.kindergartern.presentation.main;

import android.content.Intent;
import android.view.View;

import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;
import com.junbaole.kindergartern.widget.ImageSelectorView.ImageSelectorActivity;

/**
 * Created by TigerYao on 16/7/19.
 */
public class MainClickHandler {
    private MainActivity mainActivity;

    public MainClickHandler(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void onClickCamera(View view){
        Intent intent = new Intent(mainActivity, ImageSelectorActivity.class);
        SkipActivityUtils.startActivity(mainActivity,view,"",intent);
    }
}
