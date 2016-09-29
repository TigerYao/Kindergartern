package com.tencent.qcloud.ui;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.ThumbnailUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

import com.zcw.togglebutton.R;


public class SlipButton extends View implements OnTouchListener, OnClickListener {
    private boolean mChecked = false;

    private boolean mOnSlip = false;
    private double mNowX = -1;

    private OnChangedListener mChangeListener;

    private Bitmap mBitmapOn, mBitmapOff, mSlipBall;

    public SlipButton(Context context) {
        super(context);
    }

    public SlipButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlipButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        // final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.widget_slipbutton_check_on, options);
        double wscale = (double)widthSize / ((double)options.outWidth);
        double hscale = (double)heightSize / ((double)options.outHeight);
        BitmapFactory.decodeResource(getResources(), R.drawable.widget_slipbutton_check_ball, options);
        int ballWidth = (int)(options.outWidth * wscale);
        int ballHeight = (int)(options.outHeight * hscale);
        mBitmapOn = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeResource(getResources(), R.drawable.widget_slipbutton_check_on), widthSize, heightSize);
        mBitmapOff = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeResource(getResources(), R.drawable.widget_slipbutton_check_off), widthSize, heightSize);
        mSlipBall = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeResource(getResources(), R.drawable.widget_slipbutton_check_ball), ballWidth, ballHeight);
        setOnTouchListener(this);
        setOnClickListener(this);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        while (width / inSampleSize > reqWidth) {
            inSampleSize++;
        }
        while (height / inSampleSize > reqHeight) {
            inSampleSize++;
        }
        return inSampleSize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        double x;

        if (mNowX < (double)(mBitmapOn.getWidth() / 2.0)) {
            x = mNowX - (double)(mSlipBall.getWidth() / 2.0);
            canvas.drawBitmap(mBitmapOff, 0, 0, null);
        } else {
            x = mBitmapOn.getWidth() - mSlipBall.getWidth() / 2.0;
            canvas.drawBitmap(mBitmapOn, 0, 0, null);
        }

        if (mOnSlip) {
            if (mNowX >= mBitmapOn.getWidth())
                x = mBitmapOn.getWidth() - mSlipBall.getWidth() / 2.0;
            else if (mNowX < 0) {
                x = 0;
            } else {
                x = mNowX - (double)(mSlipBall.getWidth() / 2.0);
            }
        } else {
            x = mChecked ? mBitmapOn.getWidth() - mSlipBall.getWidth() : 0;
        }

        if (x < 0)
            x = 0;
        else if (x > mBitmapOn.getWidth() - mSlipBall.getWidth())
            x = mBitmapOn.getWidth() - mSlipBall.getWidth();

        canvas.drawBitmap(mSlipBall, (float)x, 0, null);
    }

    public boolean onTouch(View v, MotionEvent event) {
        boolean hasMoved = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mNowX = event.getX();
                hasMoved = true;
                break;

            case MotionEvent.ACTION_DOWN:
                if (event.getX() > mBitmapOn.getWidth() || event.getY() > mBitmapOn.getHeight())
                    return false;
                mOnSlip = true;
                mNowX = event.getX();
                hasMoved = false;
                break;

            case MotionEvent.ACTION_UP:
                mNowX = event.getX();
                if (!hasMoved) {
                    v.performClick();
                    break;
                }
            case MotionEvent.ACTION_CANCEL:
                mOnSlip = false;
                boolean oldChoose = mChecked;

                if (mNowX >= (mBitmapOn.getWidth() / 2.0)) {
                    mNowX = mBitmapOn.getWidth() - mSlipBall.getWidth() / 2.0;
                    mChecked = true;
                } else {
                    mNowX = mNowX - (double)(mSlipBall.getWidth() / 2.0);
                    mChecked = false;
                }

                if (mChangeListener != null && (oldChoose != mChecked))
                    mChangeListener.onChanged(this, mChecked);
                break;
            default:
        }

        invalidate();
        return true;
    }

    public void setOnChangedListener(OnChangedListener l) {
        mChangeListener = l;
    }

    public interface OnChangedListener {
        abstract void onChanged(SlipButton view, boolean checkState);
    }

    public void setChecked(boolean isChecked) {
        if (isChecked == mChecked)
            return;

        mChecked = isChecked;

        if (mChecked) {
            mNowX = Integer.MAX_VALUE;
        } else {
            mNowX = 0;
        }

        if (mChangeListener != null)
            mChangeListener.onChanged(this, mChecked);
        invalidate();
    }

    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void onClick(View view) {
        setChecked(!mChecked);
    }
}
