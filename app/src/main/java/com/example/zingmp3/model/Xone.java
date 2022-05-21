package com.example.zingmp3.model;

public class Xone {
    private String xoneID;
    private String xoneTitle;
    private String xoneThumb;

    public Xone(String xoneID, String xoneTitle, String xoneThumb) {
        this.xoneID = xoneID;
        this.xoneTitle = xoneTitle;
        this.xoneThumb = xoneThumb;
    }

    public String getXoneID() {
        return xoneID;
    }

    public void setXoneID(String xoneID) {
        this.xoneID = xoneID;
    }

    public String getXoneTitle() {
        return xoneTitle;
    }

    public void setXoneTitle(String xoneTitle) {
        this.xoneTitle = xoneTitle;
    }

    public String getXoneThumb() {
        return xoneThumb;
    }

    public void setXoneThumb(String xoneThumb) {
        this.xoneThumb = xoneThumb;
    }
}
