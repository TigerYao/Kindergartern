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
    public String user_id;
    public long id;
    public boolean isDiray = false;
    public String uuid = UUID.randomUUID().toString();


    public String getLastImgId() {
        int count = imageList.size();
        return imageList.get(count-1).upload_path;
    }


    public SendMessageInfo DiaryInfoToSendMessage(DiaryDetailInfo diaryDetailInfo){
        this.content = diaryDetailInfo.message;
        this.images = diaryDetailInfo.image_list;
        this.location = diaryDetailInfo.location;
        this.location_name = diaryDetailInfo.location_name;
        this.user_id = diaryDetailInfo.user.user_id;
        this.isDiray = diaryDetailInfo.isDiary;
        return this;
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

    @Override
    public String toString() {
        return "SendMessageInfo{" +
                "access_token='" + access_token + '\'' +
                ", content='" + content + '\'' +
                ", images=" + images +
                ", imageList=" + imageList +
                ", location=" + location +
                ", location_name='" + location_name + '\'' +
                ", shared_id='" + shared_id + '\'' +
                ", shared_type='" + shared_type + '\'' +
                ", user_id='" + user_id + '\'' +
                ", id=" + id +
                ", isDiray=" + isDiray +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
