package com.example.iremmutlu.gps;

import com.google.gson.annotations.SerializedName;

/**
 * Created by irem mutlu on 28.12.2016.
 */
public class OverviewPolyLine {

    @SerializedName("points")
    public String points;

    public String getPoints() {
        return points;
    }
}
