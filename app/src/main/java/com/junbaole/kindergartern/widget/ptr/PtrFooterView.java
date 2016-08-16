package com.junbaole.kindergartern.widget.ptr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.junbaole.kindergartern.R;


public class PtrFooterView extends FrameLayout implements PtrLayout.PtrLoadMoreTrigger, PtrTrigger {
    protected Context mContext;
    protected ProgressBar mFooterProgress;

    public PtrFooterView(Context context) {
        this(context, null);
    }

    public PtrFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PtrFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initViews();
    }

    private void initViews() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ptr_footer, this);
        mFooterProgress = (ProgressBar)view.findViewById(R.id.ptr_footer_progressbar);
    }

    @Override
    public void onLoadMore() {
        mFooterProgress.setVisibility(VISIBLE);
    }

    @Override
    public void onPrepare() {
        mFooterProgress.setVisibility(VISIBLE);
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
    }

    @Override
    public void onRelease() {}

    @Override
    public void onComplete() {
        mFooterProgress.setVisibility(GONE);
    }

    @Override
    public void onReset() {
        mFooterProgress.setVisibility(GONE);
    }
}