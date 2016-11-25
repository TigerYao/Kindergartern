package com.junbaole.kindergartern.presentation.personal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.PersonalLevelModel;
import com.junbaole.kindergartern.presentation.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;

public class MyLevelActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_level);
        actionManager.getLevels();
    }

    @Subscribe
    public void getLevelsResult(PersonalLevelModel personalLevelModel){

    }
}
