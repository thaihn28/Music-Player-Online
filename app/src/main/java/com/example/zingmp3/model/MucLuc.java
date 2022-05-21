package com.example.zingmp3.model;

public class MucLuc {
    private String mlName;
    private int mlImage;

    public MucLuc(String mlName, int mlImage) {
        this.mlName = mlName;
        this.mlImage = mlImage;
    }

    public String getMlName() {
        return mlName;
    }

    public void setMlName(String mlName) {
        this.mlName = mlName;
    }

    public int getMlImage() {
        return mlImage;
    }

    public void setMlImage(int mlImage) {
        this.mlImage = mlImage;
    }
}
