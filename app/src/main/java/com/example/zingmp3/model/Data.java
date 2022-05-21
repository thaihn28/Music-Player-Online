package com.example.zingmp3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("128")
    @Expose
    private String _128;
    @SerializedName("320")
    @Expose
    private String _320;

    public String get128() {
        return _128;
    }

    public void set128(String _128) {
        this._128 = _128;
    }

    public String get320() {
        return _320;
    }

    public void set320(String _320) {
        this._320 = _320;
    }

}
