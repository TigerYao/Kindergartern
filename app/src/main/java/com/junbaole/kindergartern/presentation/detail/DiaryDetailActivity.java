package com.junbaole.kindergartern.presentation.detail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.DiaryDetailInfo;
import com.junbaole.kindergartern.data.model.SendMessageInfo;
import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;
import com.junbaole.kindergartern.data.utils.event.ActionSheetEvent;
import com.junbaole.kindergartern.databinding.ActivityDiaryDetailBinding;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseFragment;
import com.junbaole.kindergartern.presentation.base.TitleBuilder;
import com.junbaole.kindergartern.presentation.photo.ImagePagerFragment;
import com.junbaole.kindergartern.presentation.send.SendActivity;

import org.greenrobot.eventbus.Subscribe;

public class DiaryDetailActivity extends BaseActivity {

    private DiaryDetailInfo diaryDetailInfo;
    private ActivityDiaryDetailBinding diaryDetailBinding;
    private BaseFragment pagerFragment;
    private DiaryDetailClickHandler clickHandler;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        diaryDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_diary_detail);
        diaryDetailInfo = getIntent().getParcelableExtra("diaryDetailInfo");
        position = getIntent().getIntExtra("position",0);
        clickHandler = new DiaryDetailClickHandler(this,diaryDetailInfo);
        diaryDetailBinding.setClickHandler(clickHandler);
        if (diaryDetailInfo != null && diaryDetailInfo.image_list.size() > 0) {
            diaryDetailBinding.setDiaryInfo(diaryDetailInfo);
            dispalyImgs();
            setTitle();
        }else{
            dispalyNoImgs();
        }
        clickHandler.setDiaryDetailInfo(diaryDetailInfo);
    }



    private void setTitle() {
        new TitleBuilder(diaryDetailBinding.titleBar).TitleBuilderLayout(true, true).TitleBuilderLeftItem(true, false).TitleBuilderRightItem(true, false).TitleBuilderImgReasours(R.mipmap.icon_fanhui,
                R.mipmap.icon_caidan_na).build();
    }

    private void dispalyImgs() {
        pagerFragment = ImagePagerFragment.newInstance(diaryDetailInfo.message, diaryDetailInfo.image_list, position,View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.display_imgs, pagerFragment).commit();
    }
    private void dispalyNoImgs() {
        pagerFragment = ContentDetailFragment.newInstance(diaryDetailInfo,false);
        getSupportFragmentManager().beginTransaction().replace(R.id.display_imgs, pagerFragment).commit();
        diaryDetailBinding.titleBar.getRoot().setVisibility(View.GONE);
    }

    protected void dispalyPinglun() {
        pagerFragment = ContentDetailFragment.newInstance(diaryDetailInfo,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.display_imgs, pagerFragment).commit();
        diaryDetailBinding.titleBar.getRoot().setVisibility(View.GONE);
        clickHandler.onClickComment(null);
    }

    @Subscribe
    public void ActionSheet(ActionSheetEvent event){
        switch (event.style){
            case "下载原图":
                Toast.makeText(this,event.style,Toast.LENGTH_LONG).show();
                break;
            case "转入日记":
                diaryDetailInfo.isDiary = true;
            case "编辑":
                Toast.makeText(this,event.style,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, SendActivity.class);
                intent.putExtra("messageInfo",diaryDetailInfo.diaryInfoToSendMessage());
                SkipActivityUtils.startActivity(this,null,"",intent);
                break;
        }
    }
}
