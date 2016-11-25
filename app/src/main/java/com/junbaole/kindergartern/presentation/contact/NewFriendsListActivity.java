package com.junbaole.kindergartern.presentation.contact;

import org.greenrobot.eventbus.Subscribe;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.NewFriendModle;
import com.junbaole.kindergartern.data.utils.event.CofirmFriendShipEvent;
import com.junbaole.kindergartern.databinding.ActivityListviewBinding;
import com.junbaole.kindergartern.presentation.adapter.NewFriendsAdapter;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseTitleClickHandler;
import com.junbaole.kindergartern.presentation.base.TitleBuilder;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

//新朋友
public class NewFriendsListActivity extends BaseActivity {
//    private View layout_head;
    private NewFriendsAdapter mAdapter;
    private ActivityListviewBinding listviewBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        listviewBinding = DataBindingUtil.setContentView(this,R.layout.activity_listview);
        super.onCreate(savedInstanceState);
        listviewBinding.layoutTitle.setClickHandler(new BaseTitleClickHandler(this));
        new TitleBuilder(listviewBinding.layoutTitle).TitleBuilderLayout(true,false).TitleBuilderLeftItem(true,false).TitleBuilderLable("新朋友","","").build();
        actionManager.getNewFriendsList();
    }

    @Override
    protected void initControl() {
//        layout_head = getLayoutInflater().inflate(
//                R.layout.layout_head_newfriend, null);
//        listviewBinding.listview.addHeaderView(layout_head);
        mAdapter = new NewFriendsAdapter(this);
        listviewBinding.listview.setAdapter(mAdapter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {}

    @Override
    protected void setListener() {

    }

    @Subscribe
    public void onGetListEvent(NewFriendModle infos){
        mAdapter.setNewFriendInfos(infos.content);
    }

    @Subscribe
    public void CofirmFriendShipEvent(CofirmFriendShipEvent event){
     actionManager.getNewFriendsList();
    }
}
