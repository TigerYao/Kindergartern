package com.junbaole.kindergartern.presentation.login;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.junbaole.kindergartern.data.model.BaseReponseModel;
import com.junbaole.kindergartern.data.model.UserLoginVO;
import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;
import com.junbaole.kindergartern.domain.Action;
import com.junbaole.kindergartern.network.RetrofitServer;
import com.junbaole.kindergartern.presentation.base.BaseTitleClickHandler;
import com.junbaole.kindergartern.presentation.main.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liangrenwang on 16/5/9.
 */
public class LoginClickHandler extends BaseTitleClickHandler{

    LoginActivity mActivity;
    Action action;

    public LoginClickHandler(Activity mActivity) {
        super(mActivity);
        action = RetrofitServer.getInstance().createService(Action.class);
    }

    public void login(final View view) {
        UserLoginVO loginVO = new UserLoginVO();
        loginVO.phone_num = "18911065028";
        loginVO.login_type = 0;
        loginVO.wd = "123456";
        action.login(loginVO).enqueue(new Callback<BaseReponseModel>() {
            @Override
            public void onResponse(Call<BaseReponseModel> call, Response<BaseReponseModel> response) {
                if(response!=null){
                    Intent intent = new Intent(mActivity, MainActivity.class);
                    SkipActivityUtils.startActivity(mActivity, view, "main_activity", intent);
                }
            }

            @Override
            public void onFailure(Call<BaseReponseModel> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void onClickReturn(View view) {
        SkipActivityUtils.specialFinish(mActivity);
    }

    public void onClickForgetPsw(View view) {

    }


}
