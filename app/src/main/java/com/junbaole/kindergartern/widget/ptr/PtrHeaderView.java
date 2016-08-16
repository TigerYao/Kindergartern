package com.junbaole.kindergartern.widget.ptr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.junbaole.kindergartern.R;


public class PtrHeaderView extends FrameLayout implements PtrLayout.PtrRefreshTrigger, PtrTrigger {
    protected Context mContext;
    private View mDefaultView;
    private TextView mResultView;

    public PtrHeaderView(Context context) {
        this(context, null);
    }

    public PtrHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PtrHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initViews();
    }

    private void initViews() {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.ptr_header, this);
        mDefaultView = header.findViewById(R.id.ptr_header_default);
        mResultView = (TextView)header.findViewById(R.id.ptr_header_result);
    }

    public void updateResult(String result) {
        mResultView.setText(result);
    }

    @Override
    public void onRefresh() {
        mDefaultView.setVisibility(VISIBLE);
        mResultView.setVisibility(GONE);
    }

    @Override
    public void onPrepare() {}

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            mDefaultView.setVisibility(VISIBLE);
            mResultView.setVisibility(GONE);
        }
    }

    @Override
    public void onRelease() {}

    @Override
    public void onComplete() {
        mDefaultView.setVisibility(GONE);
        mResultView.setVisibility(VISIBLE);
        mResultView.setText("Upadted %s hottest share res");
    }

    @Override
    public void onReset() {
        mDefaultView.setVisibility(VISIBLE);
        mResultView.setVisibility(GONE);
    }

}