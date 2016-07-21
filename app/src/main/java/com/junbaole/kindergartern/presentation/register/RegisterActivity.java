package com.junbaole.kindergartern.presentation.register;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.databinding.ActivityRegisterBinding;
import com.junbaole.kindergartern.presentation.base.BaseActivity;

public class RegisterActivity extends BaseActivity {

    public ActivityRegisterBinding registerBinding;
    private RegisterClickHandler clickHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = DataBindingUtil.setContentView(this,R.layout.activity_register);
        clickHandler = new RegisterClickHandler(this);
        registerBinding.setClickHandler(clickHandler);
        registerBinding.setIsRight(false);
        registerBinding.phoneNum.registerTelphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=11&&s.toString().startsWith("1")){
                    registerBinding.setIsRight(true);
                }else
                    registerBinding.setIsRight(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
