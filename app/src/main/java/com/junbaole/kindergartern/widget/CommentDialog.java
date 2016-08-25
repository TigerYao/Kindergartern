package com.junbaole.kindergartern.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.utils.ScreenUtils;
import com.junbaole.kindergartern.databinding.ActivityCommentsBinding;

/**
 * Created by yaohu on 16/8/25.
 */
public abstract class CommentDialog extends DialogFragment {
    InputMethodManager imm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.ActionSheetDialogStyle);
        imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        params.x = 0;
        params.y = 0;
        dialog.getWindow().setGravity(Gravity.LEFT | Gravity.BOTTOM);
        dialog.getWindow().setAttributes(params);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setOnKeyListener(mKeyListener);
        return dialog;
    }

    public abstract void onOk(String password);

    ActivityCommentsBinding commentsBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        commentsBinding = DataBindingUtil.inflate(inflater, R.layout.activity_comments, container, false);
        View view = commentsBinding.getRoot();
        view.setMinimumWidth(ScreenUtils.width);
        commentsBinding.sendComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(commentsBinding.commentsContent.getText()))
                    onOk(commentsBinding.commentsContent.getText().toString());
                dismiss();
            }
        });
        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }

    DialogInterface.OnKeyListener mKeyListener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            return (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0);
        }
    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imm.showSoftInput(commentsBinding.sendComments,InputMethodManager.SHOW_FORCED);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);

    }
}
