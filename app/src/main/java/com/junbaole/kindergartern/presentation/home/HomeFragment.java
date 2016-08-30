package com.junbaole.kindergartern.presentation.home;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.utils.amaputils.AmapQueryUtils;
import com.junbaole.kindergartern.data.utils.event.DataRefreshEvent;
import com.junbaole.kindergartern.data.utils.event.DiaryEvent;
import com.junbaole.kindergartern.databinding.FragmentHomeBinding;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseFragment;
import com.junbaole.kindergartern.presentation.base.TitleBuilder;
import com.junbaole.kindergartern.widget.ptr.PtrLayout;

import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements PtrLayout.OnLoadMoreListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private boolean  isDairy = false;
    private String mParam2;

    private FragmentHomeBinding homeBinding;
    private BaseActivity mActivity;
    private RecorderAdapter mAdapter;
    private AtomicInteger pageSize = new AtomicInteger(0);
    private int maxPage = 1;
    private HomeClickHandler homeClickHandler;

    public HomeFragment() {
        // Required empty public constructor
        super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param isDairy Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(boolean isDairy, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, isDairy);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isDairy = getArguments().getBoolean(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        homeClickHandler = new HomeClickHandler((BaseActivity) getActivity());
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        homeBinding.swipeTarget.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new RecorderAdapter();
        homeBinding.swipeTarget.setAdapter(mAdapter);
        homeBinding.setClickHandler(homeClickHandler);
        String title = "宝宝日记";
        if(isDairy){
            title = "个人主页";
        }
        new TitleBuilder(homeBinding.titleBar).TitleBuilderLayout(false, true).TitleBuilderLable(title, "", "").TitleBuilderRightItem(true, false).TitleBuilderImgReasours(-1, R.mipmap.icon_xiaoxi)
                .build();
        homeBinding.headerHome.setWeekday(getWeekDay());
        homeBinding.swipeToLoadLayout.setRefreshEnabled(false);
        homeBinding.swipeToLoadLayout.setOnLoadMoreListener(this);
        if(isDairy){
            homeBinding.headerHome.weatherLayout.setVisibility(View.GONE);
        }
        return homeBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new AmapQueryUtils().queryWeather(mActivity);
        showDialog();
        mActivity.secondActionManager.getCommonts(mActivity.getUserInfo().id, 0, isDairy);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity)activity;
    }

    @Subscribe
    public void onWeatherCallBack(LocalWeatherLiveResult localWeatherLiveResult) {
        homeBinding.setWeatherInfo(localWeatherLiveResult.getLiveResult());

    }

    @Subscribe
    public void refresh(DataRefreshEvent event) {
        pageSize.set(0);
        mActivity.secondActionManager.getCommonts(mActivity.getUserInfo().id, 0, false);

    }

    @Subscribe
    public void onGetDataByUrl(DiaryEvent event) {
        if (!event.isDiary && event.diaryInfo != null)
            if (event.diaryInfo._content != null) {
                if (pageSize.getAndIncrement() > 0) {
                    mAdapter.addDetails(event.diaryInfo._content);
                } else {
                    if (maxPage != event.diaryInfo._total_pages)
                        maxPage = event.diaryInfo._total_pages;
                    mAdapter.setDetailInfoArrayList(event.diaryInfo._content,isDairy);
                }
            }
        dismissDialog();
        homeBinding.swipeToLoadLayout.setLoadingMore(false);
    }

    String weeks[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

    private String getWeekDay() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int mWay = c.get(Calendar.DAY_OF_WEEK);
        return weeks[mWay - 1];
    }

    @Override
    public void onLoadMore() {
        Log.i("home",pageSize.get()+"????"+maxPage);
        if (pageSize.get() <= maxPage)
            mActivity.secondActionManager.getCommonts(mActivity.getUserInfo().id, pageSize.get(), false);
        else
            homeBinding.swipeToLoadLayout.setLoadingMore(false);

    }
}
