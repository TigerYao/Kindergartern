package com.junbaole.kindergartern.presentation.diary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.home.HomeFragment;

public class DiaryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        HomeFragment homeFragment = HomeFragment.newInstance(true,"");
        getSupportFragmentManager().beginTransaction().replace(R.id.diary_content,homeFragment).commit();
    }
}
