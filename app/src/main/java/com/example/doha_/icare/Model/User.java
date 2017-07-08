package com.example.doha_.icare.Model;

import java.util.List;

/**
 * Created by Ahmed on 4/29/2017.
 */

public class User {

    @com.google.gson.annotations.SerializedName("users")
    private List<Users> users;

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public static class Users {
        @com.google.gson.annotations.SerializedName("user_id")
        private int userId;
        @com.google.gson.annotations.SerializedName("fName")
        private String fname;
        @com.google.gson.annotations.SerializedName("lName")
        private String lname;
        @com.google.gson.annotations.SerializedName("email")
        private String email;
        @com.google.gson.annotations.SerializedName("photo")
        private String photo;
        @com.google.gson.annotations.SerializedName("last_time")
        private String lastTime;
        @com.google.gson.annotations.SerializedName("online")
        private int online;
        @com.google.gson.annotations.SerializedName("latitude")
        private double latitude;
        @com.google.gson.annotations.SerializedName("longitude")
        private double longitude;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getLastTime() {
            return lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public int getOnline() {
            return online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}

