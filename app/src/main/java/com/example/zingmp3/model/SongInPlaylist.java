package com.example.zingmp3.model;

public class SongInPlaylist {
    private String encodeID;
    private String title;
    private String thumbnailM;
    private String artistsNames;

    public SongInPlaylist(String encodeID, String title, String thumbnailM, String artistsNames) {
        this.encodeID = encodeID;
        this.title = title;
        this.thumbnailM = thumbnailM;
        this.artistsNames = artistsNames;
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
}
