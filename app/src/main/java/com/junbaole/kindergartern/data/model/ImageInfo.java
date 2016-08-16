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
    }

    public ImageInfo() {
    }

    protected ImageInfo(Parcel in) {
        this.uri = in.readParcelable(Uri.class.getClassLoader());
        this.realpath = in.readString();
        this.client_id = in.readString();
        this.auth = in.readString();
    }

    public static final Parcelable.Creator<ImageInfo> CREATOR = new Parcelable.Creator<ImageInfo>() {
        @Override
        public ImageInfo createFromParcel(Parcel source) {
            return new ImageInfo(source);
        }

        @Override
        public ImageInfo[] newArray(int size) {
            return new ImageInfo[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if(o instanceof ImageInfo) {
            ImageInfo imageInfo = (ImageInfo) o;
            return imageInfo.client_id.equals(this.client_id);
        }
        return false;
    }

}