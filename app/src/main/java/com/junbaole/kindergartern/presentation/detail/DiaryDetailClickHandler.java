package com.junbaole.kindergartern.presentation.detail;

import android.content.Intent;
import android.view.View;

import com.junbaole.kindergartern.data.model.CommentModel;
import com.junbaole.kindergartern.data.model.DiaryDetailInfo;
import com.junbaole.kindergartern.data.utils.ActionsheetHelper;
import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseTitleClickHandler;
import com.junbaole.kindergartern.widget.CommentDialog;

import java.util.UUID;

/**
 * Created by yaohu on 16/8/24.
 */
public class DiaryDetailClickHandler extends BaseTitleClickHandler {
    ActionsheetHelper helper;
    CommentModel commentModel;
    DiaryDetailInfo diaryDetailInfo;
    public DiaryDetailClickHandler(BaseActivity mActivity,DiaryDetailInfo diaryDetailInfo) {
        super(mActivity);
        helper = new ActionsheetHelper(mActivity, "", "下载原图", "转入日记", "编辑") {
            @Override
            public void showDialog(Object obje) {
                super.showDialog(obje);
            }
        };
        commentModel = new CommentModel();
        this.diaryDetailInfo = diaryDetailInfo;

    }

    public void setDiaryDetailInfo(DiaryDetailInfo diaryDetailInfo){
        commentModel.source_user_id =mActivity.getUserInfo().user_id;
        commentModel.moment_id = diaryDetailInfo.id;
    }
    @Override
    public void onClickReturn(View view) {
        super.onClickReturn(view);
    }

    @Override
    public void onClickRightImg(View view) {
        super.onClickRightImg(view);
        helper.showDialog("");
    }

    public void onClickComment(View view){
        CommentDialog commentDialog = new CommentDialog() {
            @Override
            public void onOk(String password) {
                commentModel.content = password;
                mActivity.secondActionManager.judge(commentModel);
            }
        };
        commentDialog.show(mActivity.getSupportFragmentManager(),"commentdialog");
    }

    public void onClickCommentBtn(View view){
        ((DiaryDetailActivity)mActivity).dispalyPinglun();
    }

    public void onClickLike(View view){
        mActivity.secondActionManager.favorite(diaryDetailInfo.id,mActivity.getUserInfo().user_id, UUID.randomUUID().toString());

    }
}
