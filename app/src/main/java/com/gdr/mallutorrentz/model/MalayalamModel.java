package com.gdr.mallutorrentz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MalayalamModel {
    @SerializedName("thumbnaill")
    @Expose
    private String thumbnaill;

    public String getAThumbnaill() {
        return thumbnaill;
    }

    public void setAThumbnaill(String thumbnaill) {
        this.thumbnaill = thumbnaill;
    }
}
