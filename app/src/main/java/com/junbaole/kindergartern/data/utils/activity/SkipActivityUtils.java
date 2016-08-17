package com.junbaole.kindergartern.data.utils.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.utils.ArrayUtils;
import com.junbaole.kindergartern.data.utils.SdkVersionUtils;


/**
 * Created by yuanzheng on 15/11/6.
 */
public class SkipActivityUtils {
    public static final boolean isOpen=true;
    public static void startActivity(Activity activity, View view, String transition_id, Intent intent) {
        if (isOpen&&view != null && SdkVersionUtils.hasLollipop()) {
            ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                    view, transition_id);
            ActivityCompat.startActivity(activity, intent, compat.toBundle());
        } else {
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.tran_pre_in_start, R.anim.tran_pre_out_start);// replace the system animation
        }
    }

    public static void startActivityForResult(Activity activity, View view, String transition_id, Intent intent, int requestCode) {
        if (isOpen&&view != null && SdkVersionUtils.hasLollipop()) {
            ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                    view, transition_id);
            ActivityCompat.startActivityForResult(activity, intent, requestCode, compat.toBundle());
        } else {
            activity.startActivityForResult(intent, requestCode);
            activity.overridePendingTransition(R.anim.tran_pre_in_start, R.anim.tran_pre_out_start);// replace the system animation
        }
    }

    public static void specialFinish(Activity activity) {
        if (isOpen&&SdkVersionUtils.hasLollipop()) {
            ActivityCompat.finishAfterTransition(activity);
        } else {
            activity.finish();
            activity.overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);// replace the system animation
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void startActivity(Activity activity, Pair<View, String>[] pairs, Intent intent) {
        if (isOpen&&SdkVersionUtils.hasLollipop()&& !ArrayUtils.isEmpty(pairs)) {
            ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
            activity.startActivity(intent, transitionActivityOptions.toBundle());
        }
        else {
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.tran_pre_in_start, R.anim.tran_pre_out_start);// replace the system animation
        }
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void startActivityForResult(Activity activity, Pair<View, String>[] pairs, Intent intent, int requestCode) {
        if (isOpen&&SdkVersionUtils.hasLollipop()&& !ArrayUtils.isEmpty(pairs)) {
            ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
            activity.startActivityForResult(intent,requestCode,transitionActivityOptions.toBundle());
        }
        else {
            activity.startActivityForResult(intent,requestCode);
            activity.overridePendingTransition(R.anim.tran_pre_in_start, R.anim.tran_pre_out_start);// replace the system animation
        }
    }
}
