package com.example.zingmp3.Model;

import java.io.Serializable;

public class PlayList implements Serializable {
    private String playListID;
    private String playListTitle;
    private String playListThumb;

    public PlayList(String playListID, String playListTitle, String playListThumb) {
        this.playListID = playListID;
        this.playListTitle = playListTitle;
        this.playListThumb = playListThumb;
    }

    public String getPlayListID() {
        return playListID;
    }

    public void setPlayListID(String playListID) {
        this.playListID = playListID;
    }

    public String getPlayListTitle() {
        return playListTitle;
    }

    public void setPlayListTitle(String playListTitle) {
        this.playListTitle = playListTitle;
    }

    public String getPlayListThumb() {
        return playListThumb;
    }

    public void setPlayListThumb(String playListThumb) {
        this.playListThumb = playListThumb;
    }
}
