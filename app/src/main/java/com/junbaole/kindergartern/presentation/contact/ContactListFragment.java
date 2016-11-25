package com.junbaole.kindergartern.presentation.contact;

import java.util.ArrayList;

import org.greenrobot.eventbus.Subscribe;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.UserInfo;
import com.junbaole.kindergartern.data.utils.chatutil.ChatUtil;
import com.junbaole.kindergartern.databinding.FragmentContactListBinding;
import com.junbaole.kindergartern.databinding.LayoutHeadSearchBinding;
import com.junbaole.kindergartern.presentation.adapter.ContactAdapter;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseFragment;
import com.junbaole.kindergartern.presentation.base.TitleBuilder;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link BaseFragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactListFragment} interface
 * to handle interaction events.
 * Use the {@link ContactListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactListFragment extends BaseFragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private BaseActivity ctx;
    private View  layout_head;
    private TextView mDialogText;
    private WindowManager mWindowManager;
    private FragmentContactListBinding contentDetailBinding;

    public ContactListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactListFragment newInstance(String param1, String param2) {
        ContactListFragment fragment = new ContactListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ctx = (BaseActivity)this.getActivity();
        contentDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact_list, null, false);
        mWindowManager = (WindowManager)ctx
                .getSystemService(Context.WINDOW_SERVICE);
        initViews();
        initData();
        setOnListener();
        new TitleBuilder(contentDetailBinding.titlebar).TitleBuilderLayout(false, false).TitleBuilderLable("通讯录", "", "").build();
        return contentDetailBinding.getRoot();
    }

    private void initViews() {

        mDialogText = (TextView)LayoutInflater.from(getActivity()).inflate(
                R.layout.list_position, null);
        mDialogText.setVisibility(View.INVISIBLE);
        contentDetailBinding.sideBar.setListView(contentDetailBinding.lvContact);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        mWindowManager.addView(mDialogText, lp);
        contentDetailBinding.sideBar.setTextView(mDialogText);
        layout_head = ctx.getLayoutInflater().inflate(
                R.layout.layout_head_friend, null);
        contentDetailBinding.lvContact.addHeaderView(layout_head);
        ChatUtil.getFriendList();

    }

    @Override
    public void onDestroy() {
        mWindowManager.removeView(mDialogText);
        super.onDestroy();
    }

    /**
     * 刷新页面
     */
    public void refresh() {
        initData();
    }

    public void initData() {
        ctx.actionManager.getFriendsList(ctx.getUserInfo().id);
    }

    private void setOnListener() {
        contentDetailBinding.lvContact.setOnItemClickListener(this);
        layout_head.findViewById(R.id.layout_addfriend)
                .setOnClickListener(this);
        layout_head.findViewById(R.id.layout_search).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_search:// 搜索好友及公众号
                ctx.startActivity(new Intent(ctx,SearchActivity.class));
                // Utils.start_Activity(getActivity(), SearchActivity.class);
                break;
            case R.id.layout_addfriend:// 添加好友
                ctx.startActivity(new Intent(ctx,NewFriendsListActivity.class));
                // Utils.start_Activity(getActivity(), NewFriendsListActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

    }

    @Subscribe
    public void getFriendList(ArrayList<UserInfo> userInfos) {
        contentDetailBinding.lvContact.setAdapter(new ContactAdapter(getActivity(),
                userInfos,true));
    }

}
