package com.junbaole.kindergartern.presentation.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.DiaryDetailInfo;
import com.junbaole.kindergartern.data.utils.event.ActionSheetEvent;
import com.junbaole.kindergartern.databinding.ActivityDiaryDetailBinding;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseFragment;
import com.junbaole.kindergartern.presentation.base.TitleBuilder;
import com.junbaole.kindergartern.presentation.photo.ImagePagerFragment;

import org.greenrobot.eventbus.Subscribe;

public class DiaryDetailActivity extends BaseActivity {

    private DiaryDetailInfo diaryDetailInfo;
    private ActivityDiaryDetailBinding diaryDetailBinding;
    private BaseFragment pagerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        diaryDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_diary_detail);
        DiaryDetailClickHandler clickHandler = new DiaryDetailClickHandler(this);
        diaryDetailBinding.setClickHandler(clickHandler);
        diaryDetailInfo = getIntent().getParcelableExtra("diaryDetailInfo");
        if (diaryDetailInfo != null && diaryDetailInfo.image_list.size() > 0) {
            diaryDetailBinding.setDiaryInfo(diaryDetailInfo);
            dispalyImgs();
            setTitle();
        }else{
            dispalyNoImgs();
        }

    }

    private void setTitle() {
        new TitleBuilder(diaryDetailBinding.titleBar).TitleBuilderLayout(true, true).TitleBuilderLeftItem(true, false).TitleBuilderRightItem(true, false).TitleBuilderImgReasours(R.mipmap.icon_fanhui,
                R.mipmap.icon_caidan_na).build();
    }

    private void dispalyImgs() {
        pagerFragment = ImagePagerFragment.newInstance(diaryDetailInfo.message, diaryDetailInfo.image_list, 0,View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.display_imgs, pagerFragment).commit();
    }
    private void dispalyNoImgs() {
        pagerFragment = ContentDetailFragment.newInstance(diaryDetailInfo,"");
        getSupportFragmentManager().beginTransaction().replace(R.id.display_imgs, pagerFragment).commit();
        diaryDetailBinding.titleBar.getRoot().setVisibility(View.GONE);
    }

    @Subscribe
    public void ActionSheet(ActionSheetEvent event){
        switch (event.style){
            case "下载原图":
                Toast.makeText(this,event.style,Toast.LENGTH_LONG).show();
                break;
            case "转入日记":
                Toast.makeText(this,event.style,Toast.LENGTH_LONG).show();
                break;
            case "编辑":
                Toast.makeText(this,event.style,Toast.LENGTH_LONG).show();
                break;
        }
    }
}
