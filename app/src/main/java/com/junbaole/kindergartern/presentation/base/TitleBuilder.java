package com.junbaole.kindergartern.presentation.base;

import android.widget.TextView;

import com.junbaole.kindergartern.databinding.TitlebarLayoutBinding;

/**
 * Created by yaohu on 16/7/22.
 */
public class TitleBuilder {

    private TitlebarLayoutBinding mTitlebarLayoutBinding;
    public boolean isLeftlayoutVisible = false, isRightLayoutVisible = false;
    public String mTitle, leftLable, rightLable;
    public boolean isVisibleLeftImg = false, isVisibleRightImg = false, isVisibleLeftLable = false, isVisisbleRightLable = false;

    public TitleBuilder(TitlebarLayoutBinding titlebarLayoutBinding) {
        this.mTitlebarLayoutBinding = titlebarLayoutBinding;
    }

    public TitleBuilder TitleBuilderLayout(boolean isLeftlayoutVisible, boolean isRightLayoutVisible) {
        this.isLeftlayoutVisible = isLeftlayoutVisible;
        this.isRightLayoutVisible = isRightLayoutVisible;
        return this;
    }

    public TitleBuilder TitleBuilderLable(String title, String leftLable, String rightLable) {
        this.mTitle = title;
        this.leftLable = leftLable;
        this.rightLable = rightLable;
        return this;
    }

    public TitleBuilder TitleBuilderLeftItem(boolean isVisibleLeftImg, boolean isVisibleLeftLable) {

        if (!isLeftlayoutVisible) {
            throw new NullPointerException("左视图没有显示");
        }
        this.isVisibleLeftImg = isVisibleLeftImg;
        this.isVisibleLeftLable = isVisibleLeftLable;
        return this;
    }

    public TitleBuilder TitleBuilderRightItem(boolean isVisibleRightImg, boolean isVisisbleRightLable) {
        if (!isRightLayoutVisible) {
            throw new NullPointerException("右视图没有显示");
        }
        this.isVisibleRightImg = isVisibleRightImg;
        this.isVisisbleRightLable = isVisisbleRightLable;
        return this;
    }

    public TitleBuilder TitleBuilderImgReasours(int leftImgId, int rightImgId) {
        if (isVisibleLeftImg && isLeftlayoutVisible)
            mTitlebarLayoutBinding.titleLeftImg.setImageResource(leftImgId);
        if (isVisibleRightImg && isRightLayoutVisible)
            mTitlebarLayoutBinding.titleRightImg.setImageResource(rightImgId);
        return this;
    }

    public TitlebarLayoutBinding getmTitlebarLayoutBinding() {
        return mTitlebarLayoutBinding;
    }

    public TitleBuilder TitleBuilderTextLeftDrawable(int left, int right, int top, int bottom) {
        mTitlebarLayoutBinding.titleLeftLable.setCompoundDrawablesWithIntrinsicBounds(left, right, top, bottom);
        return this;
    }

    public TitleBuilder TitleBuilderTextRightDrawable(int left, int right, int top, int bottom) {
        mTitlebarLayoutBinding.titleRightLable.setCompoundDrawablesWithIntrinsicBounds(left, right, top, bottom);
        return this;
    }

    public TitleBuilder TitleBuilderLableColor(int titleColor, int leftColor, int rightColor) {
        setTextColor(mTitlebarLayoutBinding.titlePagename, titleColor);
        setTextColor(mTitlebarLayoutBinding.titleLeftLable, leftColor);
        setTextColor(mTitlebarLayoutBinding.titleRightLable, rightColor);
        return this;
    }

    public void build() {
        this.mTitlebarLayoutBinding.setBuilder(this);
    }

    private void setTextColor(TextView textView, int textColor) {
        if (textColor>0)
            textView.setTextColor(mTitlebarLayoutBinding.getRoot().getResources().getColor(textColor));
    }
}
