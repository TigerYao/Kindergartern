package com.junbaole.kindergartern.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

import com.junbaole.kindergartern.data.utils.ScreenUtils;

/**
 * Created by catherine on 15/7/23.
 */
public class NoScrollGridView extends GridView {
    public NoScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(ScreenUtils.width >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }

}
