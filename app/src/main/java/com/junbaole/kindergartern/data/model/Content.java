
package com.junbaole.kindergartern.data.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Content {

    @SerializedName("create_time")
    private Long mCreateTime;
    @SerializedName("descTxt")
    private String mDescTxt;
    @SerializedName("id")
    private Long mId;
    @SerializedName("modify_time")
    private Long mModifyTime;
    @SerializedName("pointType")
    private String mPointType;
    @SerializedName("points")
    private Long mPoints;
    @SerializedName("relId")
    private String mRelId;
    @SerializedName("userId")
    private Long mUserId;
    @SerializedName("version")
    private Long mVersion;

    public Long getCreateTime() {
        return mCreateTime;
    }

    public void setCreateTime(Long create_time) {
        mCreateTime = create_time;
    }

    public String getDescTxt() {
        return mDescTxt;
    }

    public void setDescTxt(String descTxt) {
        mDescTxt = descTxt;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Long getModifyTime() {
        return mModifyTime;
    }

    public void setModifyTime(Long modify_time) {
        mModifyTime = modify_time;
    }

    public String getPointType() {
        return mPointType;
    }

    public void setPointType(String pointType) {
        mPointType = pointType;
    }

    public Long getPoints() {
        return mPoints;
    }

    public void setPoints(Long points) {
        mPoints = points;
    }

    public String getRelId() {
        return mRelId;
    }

    public void setRelId(String relId) {
        mRelId = relId;
    }

    public Long getUserId() {
        return mUserId;
    }

    public void setUserId(Long userId) {
        mUserId = userId;
    }

    public Long getVersion() {
        return mVersion;
    }

    public void setVersion(Long version) {
        mVersion = version;
    }

}
