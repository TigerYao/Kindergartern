package com.junbaole.kindergartern.presentation.personal;

import java.util.Arrays;
import java.util.List;

import org.greenrobot.eventbus.Subscribe;

import com.bumptech.glide.Glide;
import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.UserInfo;
import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;
import com.junbaole.kindergartern.data.utils.event.UserInfoEvent;
import com.junbaole.kindergartern.databinding.FragmentPersonalBinding;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseFragment;
import com.junbaole.kindergartern.presentation.base.TitleBuilder;
import com.junbaole.kindergartern.presentation.diary.DiaryActivity;
import com.junbaole.kindergartern.widget.RecycleViewDivider;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link BaseFragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PersonalFragment} interface
 * to handle interaction events.
 * Use the {@link PersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalFragment extends BaseFragment implements ItemAdapter.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private BaseActivity activity;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalFragment newInstance(String param1, String param2) {
        PersonalFragment fragment = new PersonalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PersonalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    FragmentPersonalBinding personalBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (BaseActivity) getActivity();
        personalBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_personal, container, false);
        personalBinding.setHandleclick(new PersonalClick());
        return personalBinding.getRoot();
    }

    private ItemAdapter mAdapter;
    private List<String> items;

    private Class[] mActivities;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        personalBinding.itemList.setLayoutManager(new LinearLayoutManager(getContext()));
        new TitleBuilder(personalBinding.titleBar).TitleBuilderLable("我","","").build();
        items =  Arrays.asList("我的宝贝", "我的主页", "实名认证", "系统设置");
        mAdapter = new ItemAdapter(items);
        mAdapter.setItemClickListener(this);
        personalBinding.itemList.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL));
        personalBinding.itemList.setAdapter(mAdapter);
        activity.actionManager.queryByPhone(activity.getUserInfo().phoneNum);
        mActivities = new Class[]{MyBabyActivity.class,DiaryActivity.class,AutherActivity.class,SysSettingsActivity.class};
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onItemClick(int position) {
        SkipActivityUtils.startActivity(getActivity(), null, "", new Intent(getContext(), mActivities[position]));
    }

    @Subscribe
    public void getUserInfo(UserInfoEvent userInfoEvent){
        UserInfo userInfo = userInfoEvent.userInfo;
        personalBinding.setUserinfo(userInfo);
        Glide.with(this).load(userInfo.levelIcon).placeholder(R.mipmap.icon_zhongzi).error(R.mipmap.icon_zhongzi).into(personalBinding.levelImg);
    }
}
