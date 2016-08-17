package com.junbaole.kindergartern.presentation.launcher;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.databinding.ActivityLauncherBinding;
import com.junbaole.kindergartern.presentation.base.BaseActivity;

public class LauncherActivity extends AppCompatActivity {

    ActivityLauncherBinding launcherBinding;
    private LauncherClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launcherBinding = DataBindingUtil.setContentView(this, R.layout.activity_launcher);
        clickHandler = new LauncherClickHandler();
        clickHandler.launcherActivity = this;
        launcherBinding.setClickHandler(clickHandler);
    }
}
