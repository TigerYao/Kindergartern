package com.junbaole.kindergartern.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TigerYao on 16/7/24.
 */
public class UserInfo implements Parcelable {

    public String phoneNum;
    public String name;
    public String nameNick;
    public boolean asValid;
    public boolean asTeacher;
    public boolean asParent;
    public int id;
    public String user_id;
    public String nick_name;
    public String avatar_uri;
    public String token;
    public String icon;
    public int points;
    public int rank;
    public String levelIcon;
    public String levelDesc;


    public String getPoints() {
        return points+"";
    }

    public UserInfo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.phoneNum);
        dest.writeString(this.name);
        dest.writeString(this.nameNick);
        dest.writeByte(this.asValid ? (byte) 1 : (byte) 0);
        dest.writeByte(this.asTeacher ? (byte) 1 : (byte) 0);
        dest.writeByte(this.asParent ? (byte) 1 : (byte) 0);
        dest.writeInt(this.id);
        dest.writeString(this.user_id);
        dest.writeString(this.nick_name);
        dest.writeString(this.avatar_uri);
        dest.writeString(this.token);
        dest.writeString(this.icon);
        dest.writeInt(this.points);
        dest.writeInt(this.rank);
        dest.writeString(this.levelIcon);
        dest.writeString(this.levelDesc);
    }

    protected UserInfo(Parcel in) {
        this.phoneNum = in.readString();
        this.name = in.readString();
        this.nameNick = in.readString();
        this.asValid = in.readByte() != 0;
        this.asTeacher = in.readByte() != 0;
        this.asParent = in.readByte() != 0;
        this.id = in.readInt();
        this.user_id = in.readString();
        this.nick_name = in.readString();
        this.avatar_uri = in.readString();
        this.token = in.readString();
        this.icon = in.readString();
        this.points = in.readInt();
        this.rank = in.readInt();
        this.levelIcon = in.readString();
        this.levelDesc = in.readString();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
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
