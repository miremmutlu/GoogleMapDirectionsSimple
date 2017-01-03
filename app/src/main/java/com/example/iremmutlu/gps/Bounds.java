package com.example.iremmutlu.gps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by irem mutlu on 28.12.2016.
 */
public class Bounds {

    @SerializedName("northeast")
    @Expose
    private Location northeast;
    @SerializedName("southwest")
    @Expose
    private Location southwest;

    /**
     *
     * @return
     * The northeast
     */
    public Location getNortheast() {
        return northeast;
    }

    /**
     *
     * @param northeast
     * The northeast
     */
    public void setNortheast(Location northeast) {
        this.northeast = northeast;
    }

    /**
     *
     * @return
     * The southwest
     */
    public Location getSouthwest() {
        return southwest;
    }

    /**
     *
     * @param southwest
     * The southwest
     */
    public void setSouthwest(Location southwest) {
        this.southwest = southwest;
    }

}
