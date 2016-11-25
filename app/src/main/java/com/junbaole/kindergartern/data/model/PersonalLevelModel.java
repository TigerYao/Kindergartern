
package com.junbaole.kindergartern.data.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PersonalLevelModel {

    @SerializedName("current_points")
    private Long mCurrentPoints;
    @SerializedName("day_points")
    private Long mDayPoints;
    @SerializedName("level")
    private Long mLevel;
    @SerializedName("level_meta_list")
    private List<LevelMetaList> mLevelMetaList;
    @SerializedName("next_level_need_points")
    private Long mNextLevelNeedPoints;
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

    public Long getDayPoints() {
        return mDayPoints;
    }

    public void setDayPoints(Long day_points) {
        mDayPoints = day_points;
    }

    public Long getLevel() {
        return mLevel;
    }

    public void setLevel(Long level) {
        mLevel = level;
    }

    public List<LevelMetaList> getLevelMetaList() {
        return mLevelMetaList;
    }

    public void setLevelMetaList(List<LevelMetaList> level_meta_list) {
        mLevelMetaList = level_meta_list;
    }

    public Long getNextLevelNeedPoints() {
        return mNextLevelNeedPoints;
    }

    public void setNextLevelNeedPoints(Long next_level_need_points) {
        mNextLevelNeedPoints = next_level_need_points;
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
