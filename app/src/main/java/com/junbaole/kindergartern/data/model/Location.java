package com.junbaole.kindergartern.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public  class Location implements Parcelable {
        public double latitude;
        public double longitude;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(this.latitude);
            dest.writeDouble(this.longitude);
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
