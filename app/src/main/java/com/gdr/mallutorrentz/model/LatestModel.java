package com.gdr.mallutorrentz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LatestModel {

    @SerializedName("thumbnaill")
    @Expose
    private String thumbnaill;

    public String getThumbnaill() {
        return thumbnaill;
    }

    public void setThumbnaill(String thumbnaill) {
        this.thumbnaill = thumbnaill;
    }

}