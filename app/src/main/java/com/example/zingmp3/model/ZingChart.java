package com.example.zingmp3.model;

import android.os.Parcel;
import android.os.Parcelable;


public class ZingChart implements Parcelable {
    private int zcSTT;
    private String zcID;
    private String zcTitle;
    private String zcArtist;
    private String zcThumb;

    public ZingChart(int zcSTT, String zcID, String zcTitle, String zcArtist, String zcThumb) {
        this.zcSTT = zcSTT;
        this.zcID = zcID;
        this.zcTitle = zcTitle;
        this.zcArtist = zcArtist;
        this.zcThumb = zcThumb;
    }
    public ZingChart(String zcID, String zcTitle, String zcArtist, String zcThumb) {
        this.zcID = zcID;
        this.zcTitle = zcTitle;
        this.zcArtist = zcArtist;
        this.zcThumb = zcThumb;
    }

    protected ZingChart(Parcel in) {
        zcSTT = in.readInt();
        zcID = in.readString();
        zcTitle = in.readString();
        zcArtist = in.readString();
        zcThumb = in.readString();
    }

    public static final Creator<ZingChart> CREATOR = new Creator<ZingChart>() {
        @Override
        public ZingChart createFromParcel(Parcel in) {
            return new ZingChart(in);
        }

        @Override
        public ZingChart[] newArray(int size) {
            return new ZingChart[size];
        }
    };

    public int getZcSTT() {
        return zcSTT;
    }

    public void setZcSTT(int zcSTT) {
        this.zcSTT = zcSTT;
    }

    public String getZcID() {
        return zcID;
    }

    public void setZcID(String zcID) {
        this.zcID = zcID;
    }

    public String getZcTitle() {
        return zcTitle;
    }

    public void setZcTitle(String zcTitle) {
        this.zcTitle = zcTitle;
    }

    public String getZcArtist() {
        return zcArtist;
    }

    public void setZcArtist(String zcArtist) {
        this.zcArtist = zcArtist;
    }

    public String getZcThumb() {
        return zcThumb;
    }

    public void setZcThumb(String zcThumb) {
        this.zcThumb = zcThumb;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(zcSTT);
        parcel.writeString(zcID);
        parcel.writeString(zcTitle);
        parcel.writeString(zcArtist);
        parcel.writeString(zcThumb);
    }
}
