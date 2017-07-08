package com.example.doha_.icare.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Asmaa on 24-Jun-17.
 */

public class GetDesase_user {


    @SerializedName("success")
    public boolean success;
    @SerializedName("data")
    public List<Data> data;

    public static class Data {
        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
        @SerializedName("desc")
        public String desc;
        @SerializedName("catid")
        public int catid;
    }
}
