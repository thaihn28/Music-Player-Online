package com.example.zingmp3.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class SongInPlaylist implements Parcelable {
    private int numOrder;
    private String encodeID;
    private String title;
    private String thumbnailM;
    private String artistsNames;

    public SongInPlaylist(int numOrder,String encodeID, String title, String thumbnailM, String artistsNames) {
        this.numOrder = numOrder;
        this.encodeID = encodeID;
        this.title = title;
        this.thumbnailM = thumbnailM;
        this.artistsNames = artistsNames;
    }

    protected SongInPlaylist(Parcel in) {
        numOrder = in.readInt();
        encodeID = in.readString();
        title = in.readString();
        thumbnailM = in.readString();
        artistsNames = in.readString();
    }

    public static final Creator<SongInPlaylist> CREATOR = new Creator<SongInPlaylist>() {
        @Override
        public SongInPlaylist createFromParcel(Parcel in) {
            return new SongInPlaylist(in);
        }

        @Override
        public SongInPlaylist[] newArray(int size) {
            return new SongInPlaylist[size];
        }
    };

    public int getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(int numOrder) {
        this.numOrder = numOrder;
    }

    public String getEncodeID() {
        return encodeID;
    }

    public void setEncodeID(String encodeID) {
        this.encodeID = encodeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailM() {
        return thumbnailM;
    }

    public void setThumbnailM(String thumbnailM) {
        this.thumbnailM = thumbnailM;
    }

    public String getArtistsNames() {
        return artistsNames;
    }

    public void setArtistsNames(String artistsNames) {
        this.artistsNames = artistsNames;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(numOrder);
        parcel.writeString(encodeID);
        parcel.writeString(title);
        parcel.writeString(thumbnailM);
        parcel.writeString(artistsNames);
    }
}
