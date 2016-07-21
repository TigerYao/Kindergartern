package com.junbaole.kindergartern.presentation.base;

import android.content.Intent;
import android.os.Bundle;

import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;

/**
 * Created by liangrenwang on 16/5/26.
 */
public abstract class BaseFragmentActivity extends BaseActivity {

    public abstract int getFragmentContentId();

    protected abstract BaseFragment getFirstFragment();

    protected void handleIntent(Intent intent) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void onAfterCreate() {
        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        if (null == getSupportFragmentManager().getFragments()) {
            BaseFragment fragment = getFirstFragment();
            if (null != fragment) {
                addFragment(fragment);
            }
        }
    }

    public void addFragment(BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName()).commitAllowingStateLoss();
        }
    }

    public void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            SkipActivityUtils.specialFinish(this);
        }
    }
}
