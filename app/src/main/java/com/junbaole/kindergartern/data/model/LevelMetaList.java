
package com.junbaole.kindergartern.data.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class LevelMetaList {

    @SerializedName("end_level")
    private Long mEndLevel;
    @SerializedName("icon")
    private String mIcon;
    @SerializedName("need_points")
    private Long mNeedPoints;
    @SerializedName("start_level")
    private Long mStartLevel;
    @SerializedName("title")
    private String mTitle;

    public Long getEndLevel() {
        return mEndLevel;
    }

    public void setEndLevel(Long end_level) {
        mEndLevel = end_level;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public Long getNeedPoints() {
        return mNeedPoints;
    }

    public void setNeedPoints(Long need_points) {
        mNeedPoints = need_points;
    }

    public Long getStartLevel() {
        return mStartLevel;
    }

    public void setStartLevel(Long start_level) {
        mStartLevel = start_level;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
