package com.example.doha_.icare.utilities;

import android.support.multidex.MultiDexApplication;

import com.cocosw.favor.FavorAdapter;
import com.example.doha_.icare.Model.Session;

/**
 * Created by Asmaa on 29-Jun-17.
 */

public class SharedPreferences  extends MultiDexApplication {

    private Session userSession;

    public Session getSession() {
        if (userSession == null) {
            userSession = new FavorAdapter.Builder(getApplicationContext()).build().create(Session.class);
        }
        return userSession;
    }
}

