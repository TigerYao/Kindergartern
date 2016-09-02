package com.junbaole.kindergartern.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

/**
 * Created by yaohu on 16/8/25.
 */
public class CommentModel implements Parcelable {
    public String app_id;
    public String content;
    public int moment_id;
    public String source_user_id;
    public String target_comment_id;
    public String target_user_id;
    public String type = "COMMENTS";
    public String uuid = UUID.randomUUID().toString();
    public String id;
    public String message;
    public String time;
    public String uri;
    public UserInfo source_user;
    public UserInfo target_user;

    public CommentModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.app_id);
        dest.writeString(this.content);
        dest.writeInt(this.moment_id);
        dest.writeString(this.source_user_id);
        dest.writeString(this.target_comment_id);
        dest.writeString(this.target_user_id);
        dest.writeString(this.type);
        dest.writeString(this.uuid);
        dest.writeString(this.id);
        dest.writeString(this.message);
        dest.writeString(this.time);
        dest.writeString(this.uri);
        dest.writeParcelable(this.source_user, flags);
        dest.writeParcelable(this.target_user, flags);
    }

    protected CommentModel(Parcel in) {
        this.app_id = in.readString();
        this.content = in.readString();
        this.moment_id = in.readInt();
        this.source_user_id = in.readString();
        this.target_comment_id = in.readString();
        this.target_user_id = in.readString();
        this.type = in.readString();
        this.uuid = in.readString();
        this.id = in.readString();
        this.message = in.readString();
        this.time = in.readString();
        this.uri = in.readString();
        this.source_user = in.readParcelable(UserInfo.class.getClassLoader());
        this.target_user = in.readParcelable(UserInfo.class.getClassLoader());
    }

    public static final Creator<CommentModel> CREATOR = new Creator<CommentModel>() {
        @Override
        public CommentModel createFromParcel(Parcel source) {
            return new CommentModel(source);
        }

        @Override
        public CommentModel[] newArray(int size) {
            return new CommentModel[size];
        }
    };
}
