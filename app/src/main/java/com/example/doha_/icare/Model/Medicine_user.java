package com.example.doha_.icare.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Asmaa on 28-Jun-17.
 */

public class Medicine_user implements Serializable {

    public List<Data> getData() {
        return data;
    }

    @SerializedName("success")
    public boolean success;
    @SerializedName("data")
    public List<Data> data;

    public static class Data implements Serializable {
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

        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
        @SerializedName("catid")
        public int catid;
        @SerializedName("disid")
        public int disid;
        @SerializedName("desc")
        public String desc;
    }
}
