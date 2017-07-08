package com.example.doha_.icare.utilities;

/**
 * Created by Yehia Fathi on 9/26/2016.
 */
import android.content.Context;
import android.content.SharedPreferences;

import com.example.doha_.icare.Model.UserLoginData;
import com.example.doha_.icare.Model.UserRegisterData_;
import com.google.gson.Gson;


public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    String username="usernسame";
    String password ="password";

    // shared pref mode
    int PRIVATE_MODE = 0;




    // Shared preferences file name
    private static final String USER_ID = "ID";
    private static final String LOGGED_IN = "logged_in";


    // Shared preferences file name
    private static final String PREF_NAME = "pref";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }


    public boolean check_login(){
        return  pref.getBoolean(LOGGED_IN,false);
    }

    public  void setLogin(){
        editor.putBoolean(LOGGED_IN,true);
        editor.commit();
    }

    public void logout() {
        editor.putBoolean(LOGGED_IN,false);
        editor.commit();
    }



    public  void  saveUserDataLogin(UserLoginData data){ // set the logged in user data
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString("USERDATA", json);
        editor.commit();
    }

    public  UserLoginData  getCurrentUSer(){ // get the logged in user data
        Gson gson = new Gson();
        String json = pref.getString("USERDATA", "");
        return gson.fromJson(json, UserLoginData.class);
    }


    public  void  saveUserDataRegister(UserRegisterData_ data){ // set the logged in user data
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString("USERDATAREGister", json);
        editor.commit();
    }

    public  UserRegisterData_  getCurrentRegister(){ // get the logged in user data
        Gson gson = new Gson();
        String json = pref.getString("USERDATAREGister", "");
        return gson.fromJson(json, UserRegisterData_.class);
    }




}
