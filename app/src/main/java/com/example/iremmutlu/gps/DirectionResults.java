package com.example.iremmutlu.gps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by irem mutlu on 28.12.2016.
 */
public class DirectionResults {

    @SerializedName("geocoded_waypoints")
    @Expose
    private List<GeocodedWaypoint> geocodedWaypoints = new ArrayList<GeocodedWaypoint>();
    @SerializedName("routes")
    @Expose
    private List<Route> routes = new ArrayList<>();
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * @return The geocodedWaypoints
     */
    public List<GeocodedWaypoint> getGeocodedWaypoints() {
        return geocodedWaypoints;
    }

    /**
     * @param geocodedWaypoints The geocoded_waypoints
     */
    public void setGeocodedWaypoints(List<GeocodedWaypoint> geocodedWaypoints) {
        this.geocodedWaypoints = geocodedWaypoints;
    }

    /**
     * @return The routes
     */
    public List<Route> getRoutes() {
        return routes;
    }

    /**
     * @param routes The routes
     */
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
