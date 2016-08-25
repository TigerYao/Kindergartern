package com.junbaole.kindergartern.presentation.send;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;
import com.amap.api.services.core.PoiItem;
import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.ImageInfo;
import com.junbaole.kindergartern.data.model.SendMessageInfo;
import com.junbaole.kindergartern.data.utils.activity.AppInfo;
import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;
import com.junbaole.kindergartern.data.utils.amaputils.AmapLocationUtil;
import com.junbaole.kindergartern.data.utils.event.LocationEvent;
import com.junbaole.kindergartern.data.utils.event.SendMsgEvent;
import com.junbaole.kindergartern.databinding.ActivitySendBinding;
import com.junbaole.kindergartern.presentation.adapter.ShooleListAdapter;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.TitleBuilder;
import com.junbaole.kindergartern.presentation.server.UpLoadImgService;
import com.junbaole.kindergartern.widget.ImageSelectorView.ImageSelectorActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class SendActivity extends BaseActivity {

    ActivitySendBinding mSendBinding;
    SendMessageInfo mSendMessageInfo;
    SendImgsAdapter mAdapter;
    SendClickHandler sendClickHandler;
    ArrayList<ImageInfo> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getIntExtra("type", 0) == 1) {
            Intent intent = new Intent(this, ImageSelectorActivity.class);
            SkipActivityUtils.startActivity(this, null, "", intent);
        }
        mSendBinding = DataBindingUtil.setContentView(this, R.layout.activity_send);
        sendClickHandler = new SendClickHandler(this);
        mSendBinding.setClickHandler(sendClickHandler);
        new TitleBuilder(mSendBinding.actionBarTitle).TitleBuilderLayout(true, true).TitleBuilderLeftItem(false, true).TitleBuilderRightItem(false, true).TitleBuilderLable("", "取消", "发送")
                .TitleBuilderLableColor(0, 0, R.color.white).build();
        mSendMessageInfo = new SendMessageInfo();
        mSendBinding.setSendMessage(mSendMessageInfo);
        if (AppInfo.latLonPoint != null) {
            mSendMessageInfo.location.latitude = (long)AppInfo.getLat(this);
            mSendMessageInfo.location.longitude = (long)AppInfo.getLon(this);
        }else{
            new AmapLocationUtil(this).start();
        }
        mSendMessageInfo.location_name = AppInfo.getLocationName(this);
        mAdapter = new SendImgsAdapter(this, datas);
        mSendBinding.imgList.setAdapter(mAdapter);
        mSendBinding.location.setText(AppInfo.getLocationName(this));
        Intent intent = new Intent(this, UpLoadImgService.class);
        startService(intent);
    }

    @Subscribe
    public void onGetImgsList(ArrayList<ImageInfo> imgs) {
        mAdapter.setDatas(imgs);
    }

    public SendMessageInfo getMessageInfo() {
        mSendMessageInfo.content = mSendBinding.messageContent.getText().toString();
        mSendMessageInfo.images = mAdapter.getDatas();
        return mSendMessageInfo;
    }

    @Subscribe
    public void onGetMessageInfo(SendMessageInfo messageInfo) {
        SendMsgEvent sendMsgEvent = new SendMsgEvent();
        sendMsgEvent.sendMessageInfo = messageInfo;
        Log.i("message","ddd"+ messageInfo.toString());
        EventBus.getDefault().post(sendMsgEvent);
        finish();
    }

    MaterialDialog dialog;

    @Subscribe
    public void showShoolList(LocationEvent event) {
        if (dialog == null || !dialog.isShowing())
            dialog = new MaterialDialog.Builder(this)
                    .title("显示当前位置").adapter(new ShooleListAdapter(event.locations) {
                        @Override
                        public void clickItem(PoiItem poiItem) {
                            mSendMessageInfo.location_name = poiItem.getTitle();
                            mSendBinding.location.setText(mSendMessageInfo.location_name);
                            mSendMessageInfo.location.latitude = (long) poiItem.getLatLonPoint().getLatitude();
                            mSendMessageInfo.location.longitude = (long) poiItem.getLatLonPoint().getLongitude();
                            dialog.dismiss();
                        }
                    }, new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)).show();
    }
}
