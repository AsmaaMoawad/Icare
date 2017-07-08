package com.example.doha_.icare.activates;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doha_.icare.Model.UserLoginData;
import com.example.doha_.icare.R;
import com.example.doha_.icare.utilities.PrefManager;
import com.example.doha_.icare.utilities.SharedPreferences;

/**
 * Created by Asmaa on 29-Jun-17.
 */

public class HomeActivity extends AppCompatActivity {
    Button loginButton , startButton;
    TextView Register_view;
    int id;

    PrefManager prefManager;
    UserLoginData userLoginData;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        prefManager  =  new PrefManager(this);
        userLoginData = new UserLoginData();

        if (((SharedPreferences) getApplication()).getSession().getId() != 0) {
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
        }

        loginButton = (Button) findViewById(R.id.buttonLogin);
        startButton = (Button) findViewById(R.id.buttonStart);
        Register_view = (TextView) findViewById(R.id.register_index);

        loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });

        startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(prefManager.getCurrentUSer() !=null ){
                        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(intent);

                    }
                    else {
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(intent);


                    }


                }
            });


        Register_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });



    }
}
