package com.junbaole.kindergartern.presentation.detail;

import java.util.UUID;

import com.junbaole.kindergartern.data.model.CommentModel;
import com.junbaole.kindergartern.data.model.DiaryDetailInfo;
import com.junbaole.kindergartern.data.utils.ActionsheetHelper;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseTitleClickHandler;
import com.junbaole.kindergartern.widget.CommentDialog;

import android.view.View;

/**
 * Created by yaohu on 16/8/24.
 */
public class DiaryDetailClickHandler extends BaseTitleClickHandler {
    ActionsheetHelper helper;
    CommentModel commentModel;
    DiaryDetailInfo diaryDetailInfo;
    String[] dialogItems;

    public DiaryDetailClickHandler(BaseActivity mActivity, DiaryDetailInfo diaryDetailInfo) {
        super(mActivity);
        commentModel = new CommentModel();
        setDiaryDetailInfo(diaryDetailInfo);
        if (diaryDetailInfo.isDiary)
            dialogItems = new String[] { "下载原图", "转入日记", "编辑" };
        else
            dialogItems = new String[] { "下载原图", "转入日记"};

        helper = new ActionsheetHelper(mActivity, "", dialogItems) {
            @Override
            public void showDialog(Object obje) {
                super.showDialog(obje);
            }
        };
    }

    public void setDiaryDetailInfo(DiaryDetailInfo diaryDetailInfo) {
        this.diaryDetailInfo = diaryDetailInfo;
        commentModel.source_user_id = mActivity.getUserInfo().user_id;
        commentModel.moment_id = diaryDetailInfo.id;
        commentModel.type = "COMMENTS";
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

    public void onClickComment(View view) {
        CommentDialog commentDialog = new CommentDialog() {
            @Override
            public void onOk(String password) {
                commentModel.content = password;
                mActivity.secondActionManager.judge(commentModel);
            }
        };
        commentDialog.show(mActivity.getSupportFragmentManager(), "commentdialog");
    }

    public void onClickCommentBtn(View view) {
        ((DiaryDetailActivity)mActivity).dispalyPinglun();
    }

    public void onClickLike(View view) {
        mActivity.secondActionManager.favorite(diaryDetailInfo.id, mActivity.getUserInfo().user_id, UUID.randomUUID().toString());

    }
}
