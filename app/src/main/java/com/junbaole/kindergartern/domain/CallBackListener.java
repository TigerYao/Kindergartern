package com.junbaole.kindergartern.domain;


import android.content.Intent;
import android.widget.Toast;

import com.junbaole.kindergartern.data.model.BaseReponseModel;
import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;
import com.junbaole.kindergartern.presentation.register.Register2Activity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TigerYao on 16/7/21.
 */
public abstract class CallBackListener<T> implements Callback<BaseReponseModel<T>> {

    @Override
    public void onResponse(Call<BaseReponseModel<T>> call, Response<BaseReponseModel<T>> response) {
        if (response != null && response.body() != null && response.body().isSuccess()) {
            onSuccess(response.body().data);
        } else {
            if (response == null || response.body() == null) {
                onFail("请求错误，稍后重试");
                return;
            }
            onFail(response.body().getString());
        }
    }

    @Override
    public void onFailure(Call<BaseReponseModel<T>> call, Throwable t) {
        onFail(t.getMessage());
    }

    public abstract void onSuccess(T t);

    public abstract void onFail(String failReason);
}
