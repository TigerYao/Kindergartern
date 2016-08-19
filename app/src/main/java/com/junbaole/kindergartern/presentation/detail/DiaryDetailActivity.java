package com.junbaole.kindergartern.presentation.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.DiaryDetailInfo;
import com.junbaole.kindergartern.databinding.ActivityDiaryDetailBinding;
import com.junbaole.kindergartern.presentation.photo.ImagePagerFragment;

public class DiaryDetailActivity extends AppCompatActivity {

    private DiaryDetailInfo diaryDetailInfo;
    private ActivityDiaryDetailBinding diaryDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        diaryDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_diary_detail);
        diaryDetailInfo = getIntent().getParcelableExtra("diaryDetailInfo");
        ImagePagerFragment imagePagerFragment = ImagePagerFragment.newInstance(diaryDetailInfo.image_list, 0);
        getSupportFragmentManager().beginTransaction().replace(R.id.display_imgs, imagePagerFragment).commit();
    }

}
