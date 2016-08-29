package com.junbaole.kindergartern.presentation.home;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.ParcelUuid;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.DiaryDetailInfo;
import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;
import com.junbaole.kindergartern.databinding.AdapterHomeHeartLayoutBinding;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseTitleClickHandler;
import com.junbaole.kindergartern.presentation.detail.DiaryDetailActivity;
import com.junbaole.kindergartern.presentation.diary.DiaryActivity;

import java.util.UUID;

/**
 * Created by TigerYao on 16/7/1.
 */
public class HomeClickHandler extends BaseTitleClickHandler {
    PopupWindow ppW;
    DiaryDetailInfo diaryDetailInfo;
    public HomeClickHandler(BaseActivity mActivity) {
        super(mActivity);
    }

    public void setDiaryDetailInfo(DiaryDetailInfo diaryDetailInfo) {
        this.diaryDetailInfo = diaryDetailInfo;
    }

    public void initPPW(Context ctx) {
        AdapterHomeHeartLayoutBinding heartLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(ctx), R.layout.adapter_home_heart_layout, (ViewGroup) null, false);
        ppW = new PopupWindow(heartLayoutBinding.getRoot(), ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        ppW.setTouchable(true);
        ppW.setOutsideTouchable(true);
        ppW.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        heartLayoutBinding.homeContentDianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ppW.dismiss();
                if(diaryDetailInfo!=null)
                mActivity.secondActionManager.favorite(diaryDetailInfo.id,mActivity.getUserInfo().user_id, UUID.randomUUID().toString());
            }
        });
        heartLayoutBinding.homeConentPinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ppW.dismiss();
                Intent intent = new Intent(mActivity, DiaryDetailActivity.class);
                intent.putExtra("diaryDetailInfo", diaryDetailInfo);
                SkipActivityUtils.startActivity(mActivity,v,"",intent);
            }
        });
    }

    public void onClickHeart(View view) {
        if (ppW.isShowing())
            ppW.dismiss();
        else
            ppW.showAsDropDown(view);
    }

    public void onClickHeader(View view){
        SkipActivityUtils.startActivity(mActivity,view,"",new Intent(mActivity, DiaryActivity.class));
    }
}
