package com.junbaole.kindergartern.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yaohu on 16/8/19.
 */
public class MomentLikes implements Parcelable {

    public UserInfo likeUser;
    public String id;
    public String ctime;
    public String uuid;

    public MomentLikes() {}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.likeUser, flags);
        dest.writeString(this.id);
        dest.writeString(this.ctime);
        dest.writeString(this.uuid);
    }

    protected MomentLikes(Parcel in) {
        this.likeUser = in.readParcelable(UserInfo.class.getClassLoader());
        this.id = in.readString();
        this.ctime = in.readString();
        this.uuid = in.readString();
    }

    public static final Creator<MomentLikes> CREATOR = new Creator<MomentLikes>() {
        @Override
        public MomentLikes createFromParcel(Parcel source) {
            return new MomentLikes(source);
        }

        @Override
        public MomentLikes[] newArray(int size) {
            return new MomentLikes[size];
        }
    };
}
