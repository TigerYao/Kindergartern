package com.junbaole.kindergartern.presentation.personal;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.databinding.ActivityMyBabyBinding;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.TitleBuilder;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

public class MyBabyActivity extends BaseActivity {

    ActivityMyBabyBinding myBabyBinding;
    BabyAdapter babyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myBabyBinding = DataBindingUtil.setContentView(this,R.layout.activity_my_baby);
        new TitleBuilder(myBabyBinding.titleBar).TitleBuilderLayout(true,false).TitleBuilderLeftItem(true,false).TitleBuilderLable("我的宝贝","","").build();
        myBabyBinding.babyList.setLayoutManager(new LinearLayoutManager(this));
        babyAdapter = new BabyAdapter();
        myBabyBinding.babyList.setAdapter(babyAdapter);

    }
}
