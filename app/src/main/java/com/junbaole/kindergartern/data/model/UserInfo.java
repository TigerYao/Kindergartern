package com.junbaole.kindergartern.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TigerYao on 16/7/24.
 */
public class UserInfo implements Parcelable {

    public String phoneNum;
    public String name;
    public boolean asValid;
    public boolean asTeacher;
    public boolean asParent;
    public int id;
    public String user_id;
    public String nick_name;
    public String avatar_uri;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.phoneNum);
        dest.writeString(this.name);
        dest.writeByte(this.asValid ? (byte) 1 : (byte) 0);
        dest.writeByte(this.asTeacher ? (byte) 1 : (byte) 0);
        dest.writeByte(this.asParent ? (byte) 1 : (byte) 0);
        dest.writeInt(this.id);
        dest.writeString(this.user_id);
        dest.writeString(this.nick_name);
        dest.writeString(this.avatar_uri);
    }

    public UserInfo() {
    }

    protected UserInfo(Parcel in) {
        this.phoneNum = in.readString();
        this.name = in.readString();
        this.asValid = in.readByte() != 0;
        this.asTeacher = in.readByte() != 0;
        this.asParent = in.readByte() != 0;
        this.id = in.readInt();
        this.user_id = in.readString();
        this.nick_name = in.readString();
        this.avatar_uri = in.readString();
    }

    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
}
