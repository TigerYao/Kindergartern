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
import com.junbaole.kindergartern.presentation.base.BaseTitleClickHandler;
import com.junbaole.kindergartern.presentation.main.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liangrenwang on 16/6/2.
 */
public class RegisterClickHandler extends BaseTitleClickHandler {

    Action action;
    public RegisterClickHandler(Activity mActivity) {
        super(mActivity);
        action = RetrofitServer.getInstance().createService(Action.class);
    }

    public void onClickNextStep(final View view) {

        RegisterActivity registerActivity = (RegisterActivity) mActivity;
        if(!registerActivity.registerBinding.getIsRight()){
            Toast.makeText(registerActivity,"请输入正确的手机号码！",Toast.LENGTH_LONG).show();
            return;
        }
        final SMSVO smsvo = new SMSVO();
        smsvo.phone_num = registerActivity.registerBinding.phoneNum.registerTelphone.getText().toString();
        action.sendPhoneNum(smsvo).enqueue(new Callback<BaseReponseModel>() {
            @Override
            public void onResponse(Call<BaseReponseModel> call, Response<BaseReponseModel> response) {
                Intent intent = new Intent(mActivity, Register2Activity.class);
                intent.putExtra("phoneNum",smsvo.phone_num);
                SkipActivityUtils.startActivity(mActivity, view, "", intent);
            }

            @Override
            public void onFailure(Call<BaseReponseModel> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void onClickNextStep2(View view) {
        Register2Activity register2Activity = (Register2Activity) mActivity;
        if(TextUtils.isEmpty(register2Activity.registerBinding.registerTelphone.getText())||StringUtils.isBlank(register2Activity.registerBinding.registerTelphone.getText().toString())){
            Toast.makeText(register2Activity,"请输入短信验证码！",Toast.LENGTH_LONG).show();
            return;
        }
        final SMSVO smsvo = new SMSVO();
        smsvo.phone_num = register2Activity.phoneNum;
        action.checkVCode(smsvo).enqueue(new Callback<BaseReponseModel>() {
            @Override
            public void onResponse(Call<BaseReponseModel> call, Response<BaseReponseModel> response) {

            }

            @Override
            public void onFailure(Call<BaseReponseModel> call, Throwable t) {

            }
        });
        SkipActivityUtils.startActivity(mActivity, view, "", new Intent(mActivity, Register3Activity.class));
    }

    public void onClickFinish(View view) {
        SkipActivityUtils.startActivity(mActivity, view, "", new Intent(mActivity, MainActivity.class));
    }
}
