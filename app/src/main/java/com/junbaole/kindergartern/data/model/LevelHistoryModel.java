
package com.junbaole.kindergartern.data.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class LevelHistoryModel {

    @SerializedName("points_his")
    private PointsHis mPointsHis;
    @SerializedName("user")
    private User mUser;

    public PointsHis getPointsHis() {
        return mPointsHis;
    }

    public void setPointsHis(PointsHis points_his) {
        mPointsHis = points_his;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

}
