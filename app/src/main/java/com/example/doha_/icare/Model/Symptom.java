package com.example.doha_.icare.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Asmaa on 19-Jun-17.
 */

public class Symptom {
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @SerializedName("success")

    public boolean success;
    @SerializedName("data")
    public List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
        @SerializedName("disid")
        public int disid;
    }
}
