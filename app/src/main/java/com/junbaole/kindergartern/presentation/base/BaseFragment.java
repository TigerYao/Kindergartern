package com.junbaole.kindergartern.presentation.base;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import com.afollestad.materialdialogs.MaterialDialog;
import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.domain.SendPhoneEvent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.print.PrintHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    public MaterialDialog materialProgressBar;

    public BaseFragment() {
        // Required empty public constructor

    }

    protected void showDialog() {
        if (!materialProgressBar.isShowing()) {
            materialProgressBar.show();
        }
    }

    protected void dismissDialog() {
        if (materialProgressBar.isShowing()) {
            materialProgressBar.dismiss();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        materialProgressBar = new MaterialDialog.Builder(getContext()).title("正在加载数据,请稍后!")
                .progress(true, 0).build();
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMainEvent(SendPhoneEvent event) {
    }
}
