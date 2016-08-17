package com.junbaole.kindergartern.presentation.register;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.junbaole.kindergartern.data.model.BaseReponseModel;
import com.junbaole.kindergartern.data.model.SMSVO;
import com.junbaole.kindergartern.data.utils.StringUtils;
import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;
import com.junbaole.kindergartern.domain.Action;
import com.junbaole.kindergartern.network.RetrofitServer;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseTitleClickHandler;
import com.junbaole.kindergartern.presentation.main.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liangrenwang on 16/6/2.
 */
public class RegisterClickHandler extends BaseTitleClickHandler {
    String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$";
    String code;

    public RegisterClickHandler(BaseActivity mActivity) {
        super(mActivity);

    }

    public void onClickNextStep(final View view) {

        RegisterActivity registerActivity = (RegisterActivity) mActivity;
        if (!registerActivity.registerBinding.getIsRight()) {
            Toast.makeText(registerActivity, "请输入正确的手机号码！", Toast.LENGTH_LONG).show();
            return;
        }

        String phone_num = registerActivity.registerBinding.phoneNum.registerTelphone.getText().toString();

        registerActivity.actionManager.sendPhoneNum(phone_num);

    }

    public void onClickNextStep2(View view) {
        Register2Activity register2Activity = (Register2Activity) mActivity;
        if (TextUtils.isEmpty(register2Activity.registerBinding.registerTelphone.getText()) || StringUtils.isBlank(register2Activity.registerBinding.registerTelphone.getText().toString())) {
            Toast.makeText(register2Activity, "请输入短信验证码！", Toast.LENGTH_LONG).show();
            return;
        }
        code = register2Activity.registerBinding.registerTelphone.getText().toString();
        register2Activity.actionManager.checkCode(register2Activity.phoneNum, Integer.parseInt(code), register2Activity.getIntent().getIntExtra("time", (int) System.currentTimeMillis() / 1000));
    }

    public void onClickFinish(View view) {
        Register3Activity register3Activity = (Register3Activity) mActivity;
        if (!register3Activity.registerBinding.getIsRight()) {
            register3Activity.registerBinding.registerPswAgain.setError("密码不一致");
            return;
        }
        String psw = register3Activity.registerBinding.registerPsw.getText().toString().trim();
        register3Activity.actionManager.registerApp(register3Activity.phoneNum, Integer.parseInt(register3Activity.code), psw, register3Activity.type, register3Activity.getIntent().getIntExtra("time", -1));
    }
}
