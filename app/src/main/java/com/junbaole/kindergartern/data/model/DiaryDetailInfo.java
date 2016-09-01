package com.junbaole.kindergartern.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by yaohu on 16/8/19.
 */
public class DiaryDetailInfo implements Parcelable {
    public Location location;

    public int id;

    public String message;

    public String publish_time;

    public UserInfo user;

    public String location_name;

    public ArrayList<ImageInfo> image_list ;

    public int like_num;

    public int comment_num;

    public ArrayList<CommentModel> comments ;

    public ArrayList<MomentLikes> moment_likes ;

    public boolean isDiary;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.location, flags);
        dest.writeInt(this.id);
        dest.writeString(this.message);
        dest.writeString(this.publish_time);
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.location_name);
        dest.writeTypedList(this.image_list);
        dest.writeInt(this.like_num);
        dest.writeInt(this.comment_num);
        dest.writeList(this.comments);
        dest.writeList(this.moment_likes);
    }

    public DiaryDetailInfo() {
    }
    public SendMessageInfo diaryInfoToSendMessage(){
        SendMessageInfo messageInfo = new SendMessageInfo();
        messageInfo.content = this.message;
        messageInfo.images = this.image_list;
        messageInfo.location = this.location;
        messageInfo.location_name = this.location_name;
        messageInfo.user_id = this.user.user_id;
        messageInfo.isDiray = this.isDiary;
        messageInfo.id = this.id;
        return messageInfo;
    }
    protected DiaryDetailInfo(Parcel in) {
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.id = in.readInt();
        this.message = in.readString();
        this.publish_time = in.readString();
        this.user = in.readParcelable(UserInfo.class.getClassLoader());
        this.location_name = in.readString();
        this.image_list = in.createTypedArrayList(ImageInfo.CREATOR);
        this.like_num = in.readInt();
        this.comment_num = in.readInt();
        this.comments = new ArrayList<CommentModel>();
        in.readList(this.comments, Comments.class.getClassLoader());
        this.moment_likes = new ArrayList<MomentLikes>();
        in.readList(this.moment_likes, MomentLikes.class.getClassLoader());
    }

    public static final Parcelable.Creator<DiaryDetailInfo> CREATOR = new Parcelable.Creator<DiaryDetailInfo>() {
        @Override
        public DiaryDetailInfo createFromParcel(Parcel source) {
            return new DiaryDetailInfo(source);
        }

        @Override
        public DiaryDetailInfo[] newArray(int size) {
            return new DiaryDetailInfo[size];
        }
    };
}
