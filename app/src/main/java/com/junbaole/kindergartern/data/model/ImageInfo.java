package com.junbaole.kindergartern.data.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class ImageInfo implements Parcelable {
    private Uri uri;
    private String realpath;
    public String client_id;
    public String auth;
    public String image_id;
    public String base_thumbnail_uri;
    public String original_uri;
    public String upload_path;
    public String bucket;
    public boolean is_exit;
    public int id;
    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public void setRealpath(String realpath) {
        this.realpath = realpath;
    }

    public Uri getImgUri() {
        return uri;
    }

    public String getImgPath() {
        return realpath;
    }

    public ImageInfo() {
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof ImageInfo) {
            ImageInfo imageInfo = (ImageInfo) o;
            return imageInfo.client_id.equals(this.client_id);
        }
        return false;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "uri=" + uri +
                ", realpath='" + realpath + '\'' +
                ", client_id='" + client_id + '\'' +
                ", auth='" + auth + '\'' +
                ", image_id='" + image_id + '\'' +
                ", base_thumbnail_uri='" + base_thumbnail_uri + '\'' +
                ", upload_path='" + upload_path + '\'' +
                ", bucket='" + bucket + '\'' +
                ", is_exit=" + is_exit +
                ", id=" + id +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.uri, flags);
        dest.writeString(this.realpath);
        dest.writeString(this.client_id);
        dest.writeString(this.auth);
        dest.writeString(this.image_id);
        dest.writeString(this.base_thumbnail_uri);
        dest.writeString(this.original_uri);
        dest.writeString(this.upload_path);
        dest.writeString(this.bucket);
        dest.writeByte(this.is_exit ? (byte) 1 : (byte) 0);
        dest.writeInt(this.id);
    }

    protected ImageInfo(Parcel in) {
        this.uri = in.readParcelable(Uri.class.getClassLoader());
        this.realpath = in.readString();
        this.client_id = in.readString();
        this.auth = in.readString();
        this.image_id = in.readString();
        this.base_thumbnail_uri = in.readString();
        this.original_uri = in.readString();
        this.upload_path = in.readString();
        this.bucket = in.readString();
        this.is_exit = in.readByte() != 0;
        this.id = in.readInt();
    }

    public static final Creator<ImageInfo> CREATOR = new Creator<ImageInfo>() {
        @Override
        public ImageInfo createFromParcel(Parcel source) {
            return new ImageInfo(source);
        }

        @Override
        public ImageInfo[] newArray(int size) {
            return new ImageInfo[size];
        }
    };
}