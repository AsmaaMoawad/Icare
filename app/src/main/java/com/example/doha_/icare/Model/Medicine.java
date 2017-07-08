package com.example.doha_.icare.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Asmaa on 05-May-17.
 */

public class Medicine {


    @SerializedName("success")
    private boolean success;
    @SerializedName("data")
    private List<Data> data;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("catid")
        private int catid;
        @SerializedName("disid")
        private int disid;
        @SerializedName("desc")
        private String desc;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCatid() {
            return catid;
        }

        public void setCatid(int catid) {
            this.catid = catid;
        }

        public int getDisid() {
            return disid;
        }

        public void setDisid(int disid) {
            this.disid = disid;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}

