package com.junbaole.kindergartern.presentation.register;

import java.util.ArrayList;

import com.afollestad.materialdialogs.MaterialDialog;
import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.ParentAuthVO;
import com.junbaole.kindergartern.data.model.ShooleInfo;
import com.junbaole.kindergartern.data.utils.StringUtils;
import com.junbaole.kindergartern.presentation.adapter.ShooleListAdapter;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseTitleClickHandler;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by TigerYao on 16/7/23.
 */
public class RealInfoClickHandler extends BaseTitleClickHandler {
    ParentAuthVO parentAuthVO;
    MaterialDialog dialog;
    public RealInfoActivity realInfoActivity;

    public RealInfoClickHandler(BaseActivity mActivity, ParentAuthVO parentAuthVO) {
        super(mActivity);
        realInfoActivity = (RealInfoActivity)mActivity;
        this.parentAuthVO = parentAuthVO;
        init();
    }

    public void onClickSave(View view) {
        if (isOk()) {
            ((RealInfoActivity)mActivity).actionManager.parentAuth(parentAuthVO);
        }
    }

    public void onClickRelationShip(View view) {
        if (dialog == null)
            init();
        if (!dialog.isShowing())
            dialog.show();
    }

    public void onClickShoolList(View view) {
        realInfoActivity.actionManager.getShoolList("");
    }

    private boolean isOk() {
        if (isNull(realInfoActivity.realInfoBinding.realPhone)) {
            return false;
        }
        parentAuthVO.phone_num = realInfoActivity.realInfoBinding.realPhone.getText().toString();
        if (isNull(realInfoActivity.realInfoBinding.realParentName)) {
            return false;
        }
        parentAuthVO.name = realInfoActivity.realInfoBinding.realParentName.getText().toString();
        if (isNull(realInfoActivity.realInfoBinding.babyName)) {
            return false;
        }
        parentAuthVO.child_name = realInfoActivity.realInfoBinding.babyName.getText().toString();
        if (isNull(realInfoActivity.realInfoBinding.schoolName)) {
            return false;
        }
        if (isNull(realInfoActivity.realInfoBinding.relationship)) {
            return false;
        }
        return true;
    }

    private boolean isNull(TextView editText) {
        if (TextUtils.isEmpty(editText.getText()) || StringUtils.isBlank(editText.getText().toString())) {
            editText.setError("请输入内容");
            Toast.makeText(realInfoActivity, "不能为空", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    private void init() {
        dialog = new MaterialDialog.Builder(mActivity)
                .title("与儿童关系")
                .items(R.array.relationship)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        ((RealInfoActivity)mActivity).realInfoBinding.relationship.setText(text);
                        parentAuthVO.rel_type = which + 1;
                        dialog.dismiss();
                    }
                }).build();
    }

}




