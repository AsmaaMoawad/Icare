package com.example.doha_.icare.activates;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.doha_.icare.Model.Medicine_user;
import com.example.doha_.icare.R;

import java.util.ArrayList;

public class Descriotion_Med extends AppCompatActivity {
    TextView DES;
    private ArrayList<String> descriptions;
    ArrayList<Medicine_user.Data> drugArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descriotion__med);

        DES = (TextView) findViewById(R.id.description);

        descriptions = getIntent().getStringArrayListExtra("description");
        drugArrayList = (ArrayList<Medicine_user.Data>) getIntent().getSerializableExtra("drugList");
        Log.d("descriptions", descriptions.size() + " items");

        for (int i = 0; i < descriptions.size(); i++) {
            if (i == 0) {
                DES.setText(drugArrayList.get(0).getName() + "" + "\n" + descriptions.get(0));


            } else {
                DES.append(drugArrayList.get(i).getName() + "\n" + descriptions.get(i) + "\n" + "\n" + "\n" );


            }
            Log.d("descriptions", descriptions.get(i));
        }


    }
}
