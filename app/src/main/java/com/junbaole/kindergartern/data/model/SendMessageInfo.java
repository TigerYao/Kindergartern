package com.junbaole.kindergartern.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by yaohu on 16/7/22.
 */
public class SendMessageInfo implements Parcelable {
    public String access_token;
    public String content;
    public ArrayList<ImageInfo> images = new ArrayList<>();
    public ArrayList<ImageInfo> imageList = new ArrayList<>();
    public Location location = new Location();
    public String location_name;
    public String shared_id;
    public String shared_type;
    public String user_id = "2";
    public long id;
    public boolean isDiray;
    public String uuid = UUID.randomUUID().toString();

    public static class Location implements Parcelable {
        public long latitude;
        public long longitude;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(this.latitude);
            dest.writeLong(this.longitude);
        }

        public Location() {
        }

        protected Location(Parcel in) {
            this.latitude = in.readLong();
            this.longitude = in.readLong();
        }

        public static final Creator<Location> CREATOR = new Creator<Location>() {
            @Override
            public Location createFromParcel(Parcel source) {
                return new Location(source);
            }

            @Override
            public Location[] newArray(int size) {
                return new Location[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.access_token);
        dest.writeString(this.content);
        dest.writeTypedList(this.images);
        dest.writeTypedList(this.imageList);
        dest.writeParcelable(this.location, flags);
        dest.writeString(this.location_name);
        dest.writeString(this.shared_id);
        dest.writeString(this.shared_type);
        dest.writeString(this.user_id);
        dest.writeLong(this.id);
        dest.writeByte(this.isDiray ? (byte) 1 : (byte) 0);
        dest.writeString(this.uuid);
    }

    public SendMessageInfo() {
    }

    protected SendMessageInfo(Parcel in) {
        this.access_token = in.readString();
        this.content = in.readString();
        this.images = in.createTypedArrayList(ImageInfo.CREATOR);
        this.imageList = in.createTypedArrayList(ImageInfo.CREATOR);
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.location_name = in.readString();
        this.shared_id = in.readString();
        this.shared_type = in.readString();
        this.user_id = in.readString();
        this.id = in.readLong();
        this.isDiray = in.readByte() != 0;
        this.uuid = in.readString();
    }

    public static final Parcelable.Creator<SendMessageInfo> CREATOR = new Parcelable.Creator<SendMessageInfo>() {
        @Override
        public SendMessageInfo createFromParcel(Parcel source) {
            return new SendMessageInfo(source);
        }

        @Override
        public SendMessageInfo[] newArray(int size) {
            return new SendMessageInfo[size];
        }
    };
}
