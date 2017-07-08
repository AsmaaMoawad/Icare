package com.example.doha_.icare.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Asmaa on 21-Jun-17.
 */

public class SymptomsDeseaseResponse {

    @SerializedName("success")
    public boolean success;
    @SerializedName("data")
    public ArrayList<Data> data;

    public static class Data implements Parcelable  {
        @SerializedName("d_name")
        public String d_name;

        protected Data(Parcel in) {
            d_name = in.readString();
        }

        public static final Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel in) {
                return new Data(in);
            }

            @Override
            public Data[] newArray(int size) {
                return new Data[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        public String getD_name() {
            return d_name;
        }

        public void setD_name(String d_name) {
            this.d_name = d_name;
        }

        public static Creator<Data> getCREATOR() {
            return CREATOR;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(d_name);
        }
    }
}
