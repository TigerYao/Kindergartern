
package com.junbaole.kindergartern.data.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class User {

    @SerializedName("current_points")
    private Long mCurrentPoints;
    @SerializedName("total_points")
    private Long mTotalPoints;
    @SerializedName("user_id")
    private Long mUserId;

    public Long getCurrentPoints() {
        return mCurrentPoints;
    }

    public void setCurrentPoints(Long current_points) {
        mCurrentPoints = current_points;
    }

    public Long getTotalPoints() {
        return mTotalPoints;
    }

    public void setTotalPoints(Long total_points) {
        mTotalPoints = total_points;
    }

    public Long getUserId() {
        return mUserId;
    }

    public void setUserId(Long user_id) {
        mUserId = user_id;
    }

}
