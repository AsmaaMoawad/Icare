package com.example.doha_.icare.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Asmaa on 21-Jun-17.
 */

public class SearchMapResponse {

    @SerializedName("html_attributions")
    public List<Html_attributions> html_attributions;
    @SerializedName("next_page_token")
    public String next_page_token;
    @SerializedName("results")
    public List<Results> results;
    @SerializedName("status")
    public String status;

    public static class Html_attributions {
    }

    public static class Location {
        @SerializedName("lat")
        public double lat;
        @SerializedName("lng")
        public double lng;
    }

    public static class Northeast {
        @SerializedName("lat")
        public double lat;
        @SerializedName("lng")
        public double lng;
    }

    public static class Southwest {
        @SerializedName("lat")
        public double lat;
        @SerializedName("lng")
        public double lng;
    }

    public static class Viewport {
        @SerializedName("northeast")
        public Northeast northeast;
        @SerializedName("southwest")
        public Southwest southwest;
    }

    public static class Geometry {
        @SerializedName("location")
        public Location location;
        @SerializedName("viewport")
        public Viewport viewport;
    }

    public static class Results {
        @SerializedName("geometry")
        public Geometry geometry;
        @SerializedName("icon")
        public String icon;
        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName("place_id")
        public String place_id;
        @SerializedName("rating")
        public double rating;
        @SerializedName("reference")
        public String reference;
        @SerializedName("scope")
        public String scope;
        @SerializedName("vicinity")
        public String vicinity;
    }
}
