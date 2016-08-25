package com.junbaole.kindergartern.presentation.detail;

import android.content.Intent;
import android.view.View;

import com.junbaole.kindergartern.data.utils.ActionsheetHelper;
import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseTitleClickHandler;
import com.junbaole.kindergartern.widget.CommentDialog;

/**
 * Created by yaohu on 16/8/24.
 */
public class DiaryDetailClickHandler extends BaseTitleClickHandler {
    ActionsheetHelper helper;

    public DiaryDetailClickHandler(BaseActivity mActivity) {
        super(mActivity);
        helper = new ActionsheetHelper(mActivity, "", "下载原图", "转入日记", "编辑") {
            @Override
            public void showDialog(Object obje) {
                super.showDialog(obje);
            }
        };
    }

    @Override
    public void onClickReturn(View view) {
        super.onClickReturn(view);
    }

    @Override
    public void onClickRightImg(View view) {
        super.onClickRightImg(view);
        helper.showDialog("nihao");
    }

    public void onClickComment(View view){
        CommentDialog commentDialog = new CommentDialog() {
            @Override
            public void onOk(String password) {
            }
        };
        commentDialog.show(mActivity.getSupportFragmentManager(),"commentdialog");
    }
}
