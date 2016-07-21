package com.junbaole.kindergartern.presentation.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.databinding.AdapterHomeHeartLayoutBinding;

/**
 * Created by TigerYao on 16/7/1.
 */
public class HomeClickHandler {
    PopupWindow ppW;

    public  void initPPW(Context ctx){
        AdapterHomeHeartLayoutBinding heartLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(ctx), R.layout.adapter_home_heart_layout,(ViewGroup) null,false);
        ppW = new PopupWindow(heartLayoutBinding.getRoot(), ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        ppW.setTouchable(true);
        ppW.setOutsideTouchable(true);
        ppW.setBackgroundDrawable(ctx.getResources().getDrawable(R.mipmap.icon_dianzan_tc_bg));
    }

    public void  onClickHeart(View view){
        if(ppW.isShowing())
            ppW.dismiss();
        else
        ppW.showAsDropDown(view,-130,0);
    }

}
