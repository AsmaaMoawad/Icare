package com.example.doha_.icare.Model;

import android.content.Context;

import com.example.doha_.icare.utilities.SharedPreferences;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asmaa on 30-Jun-17.
 */


public class UserLoginData {
    @SerializedName("success")
    public boolean success;

    public boolean isSuccess() {
        return success;
    }

    public Data getData() {
        return data;
    }

    @SerializedName("data")
    public Data data;

    public static class User {
        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
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

        @SerializedName("id")
        public String id;
        @SerializedName("pass")
        public String pass;
        @SerializedName("mail")
        public String mail;
        @SerializedName("fname")
        public String fname;
        @SerializedName("lname")
        public String lname;
        @SerializedName("phone")
        public int phone;
        @SerializedName("age")
        public int age;
    }

    public static class Data {
       @SerializedName("user")
        public User user;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public int[] getDisease_ids() {
            return disease_ids;
        }

        public void setDisease_ids(int[] disease_ids) {
            this.disease_ids = disease_ids;
        }

        @SerializedName("disease_ids")
        public int[] disease_ids;

        @SerializedName("names")
        public String[] names;
        @SerializedName("id")
        public String id;

        public int getPhone() {
            return phone;
        }

        public void setPhone(int phone) {
            this.phone = phone;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @SerializedName("pass")

        public String pass;
        @SerializedName("mail")
        public String mail;
        @SerializedName("fname")
        public String fname;
        @SerializedName("lname")
        public String lname;
        @SerializedName("phone")
        public int phone;
        @SerializedName("age")
        public int age;

        public String[] getNames() {
            return names;
        }

        public void setNames(String[] names) {
            this.names = names;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
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
    }
    public static void setUserDataPrefernces(Context context,UserLoginData userLoginData) {


        ((SharedPreferences) context.getApplicationContext()).getSession().setFname(userLoginData.getData().getFname());
            ((SharedPreferences) context.getApplicationContext()).getSession().setLname(userLoginData.getData().getLname());
            ((SharedPreferences) context.getApplicationContext()).getSession().setMail(userLoginData.getData().getMail());
            ((SharedPreferences) context.getApplicationContext()).getSession().setPass(userLoginData.getData().getPass());
            ((SharedPreferences) context.getApplicationContext()).getSession().setDisease_ids(userLoginData.getData().getDisease_ids());


    }
}
