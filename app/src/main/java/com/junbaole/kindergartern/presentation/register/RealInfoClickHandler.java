package com.junbaole.kindergartern.presentation.register;

import android.app.Activity;
import android.database.DataSetObserver;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.ParentAuthVO;
import com.junbaole.kindergartern.data.model.ShooleInfo;
import com.junbaole.kindergartern.data.utils.StringUtils;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseTitleClickHandler;

import java.util.ArrayList;


/**
 * Created by TigerYao on 16/7/23.
 */
public class RealInfoClickHandler extends BaseTitleClickHandler {
    ParentAuthVO parentAuthVO;
    MaterialDialog dialog;
    public RealInfoActivity realInfoActivity;

    public RealInfoClickHandler(BaseActivity mActivity, ParentAuthVO parentAuthVO) {
        super(mActivity);
        realInfoActivity = (RealInfoActivity) mActivity;
        this.parentAuthVO = parentAuthVO;
        init();
    }

    public void onClickSave(View view) {
        if (isOk()) {
            ((RealInfoActivity) mActivity).actionManager.parentAuth(parentAuthVO);
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
            Toast.makeText(realInfoActivity,"不能为空",Toast.LENGTH_LONG).show();
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
                        ((RealInfoActivity) mActivity).realInfoBinding.relationship.setText(text);
                        parentAuthVO.rel_type = which+1;
                        dialog.dismiss();
                    }
                }).build();
    }

    public void showShoolList(final ArrayList<ShooleInfo> list) {
        new MaterialDialog.Builder(mActivity)
                .title("选择学校").adapter(new ShooleListAdapter(list), new MaterialDialog.ListCallback() {
            @Override
            public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                realInfoActivity.realInfoBinding.schoolName.setText(list.get(which).name);
                parentAuthVO.shcool_id = list.get(which).id;
                dialog.dismiss();
            }
        }).show();
    }

}

class ShooleListAdapter extends BaseAdapter {

    ArrayList<ShooleInfo> mList;

    public ShooleListAdapter(ArrayList<ShooleInfo> list) {
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public ShooleInfo getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mList.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1, null);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getItem(i).name);
        return view;
    }
}
