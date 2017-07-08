package com.example.doha_.icare.Model;

import android.content.Context;

import com.example.doha_.icare.utilities.SharedPreferences;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asmaa on 30-Jun-17.
 */

public class UserRegisterData_ {

    @SerializedName("success")
    public boolean success;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public Data getData() {
        return data;

    }

    public void setData(Data data) {
        this.data = data;
    }

    @SerializedName("data")
    public Data data;

    public static class User {

        @SerializedName("mail")
        public String mail;

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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



        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @SerializedName("pass")
        public String pass;
        @SerializedName("age")
        public String age;
        @SerializedName("phone")
        public String phone;
        @SerializedName("fname")
        public String fname;
        @SerializedName("lname")
        public String lname;
        @SerializedName("id")
        public int id;
    }

    public static class Data {
        @SerializedName("user")
        public User user;

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
    }
    public static void setUserDataPrefernces(Context context, UserRegisterData_ UserRegisterData_) {

        ((SharedPreferences) context.getApplicationContext()).getSession().setId(UserRegisterData_.getData().user.getId());
        ((SharedPreferences) context.getApplicationContext()).getSession().setAge(UserRegisterData_.getData().user.getAge());
        ((SharedPreferences) context.getApplicationContext()).getSession().setFname(UserRegisterData_.getData().user.getFname());
        ((SharedPreferences) context.getApplicationContext()).getSession().setLname(UserRegisterData_.getData().user.getLname());
        ((SharedPreferences) context.getApplicationContext()).getSession().setMail(UserRegisterData_.getData().user.getMail());
        ((SharedPreferences) context.getApplicationContext()).getSession().setPass(UserRegisterData_.getData().user.getPass());
        ((SharedPreferences) context.getApplicationContext()).getSession().setPhone(UserRegisterData_.getData().user.getPhone());
        ((SharedPreferences) context.getApplicationContext()).getSession().setDisease_ids(UserRegisterData_.getData().getDisease_ids());


    }
}
