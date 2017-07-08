package com.example.doha_.icare.activates;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doha_.icare.Model.GetDesase_user;
import com.example.doha_.icare.Model.UserLoginData;
import com.example.doha_.icare.utilities.PrefManager;
import com.example.doha_.icare.R;
import com.example.doha_.icare.utilities.URL;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    EditText fnameEditText,lnameEditText,phoneEditText,ageEditText,
            emailEditText,passwordEditText,rePasswordEditText;
    String fname,lname,phone,age,email,password,repassword,des1_str,des2_str,des3_str;

    TextView des1,des2,des3;
    Button editButton,resetButton;

    int id;
    int x = 1;

    PrefManager prefManager;
    String new_ids = "";
    UserLoginData userLoginData;
    List<Integer> integerList = new ArrayList<>();
    GetDesase_user desase_user;
    ArrayList<String> des_arr = new ArrayList<>();
    ArrayAdapter<CharSequence> adapter;
    boolean userIsInteracting = false;
    String [] no_replicate=new String[3];
    String attemp;
    String [] ids=new String[3];
    String str_ids;
    UserLoginData temp;
    String new_ids_array[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fnameEditText = (EditText) findViewById(R.id.fnameEditText);
        lnameEditText = (EditText) findViewById(R.id.lnameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);

        des1 = (TextView)findViewById(R.id.deschoosed1);
        des2 = (TextView)findViewById(R.id.deschoosed2);
        des3 = (TextView)findViewById(R.id.deschoosed3);
        ageEditText = (EditText) findViewById(R.id.ageEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        rePasswordEditText = (EditText) findViewById(R.id.rePasswordEditText);
        editButton = (Button) findViewById(R.id.editButton);

        prefManager = new PrefManager(this);
        id= Integer.parseInt(prefManager.getCurrentUSer().getData().id);
        displayProfile();

        editButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                fname = fnameEditText.getText().toString();
                lname = lnameEditText.getText().toString();
                phone = phoneEditText.getText().toString();
                age = ageEditText.getText().toString();
                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();
                repassword = rePasswordEditText.getText().toString();

                if (!validate()) {
                    return;
                }
                updateProfile();
            }
        });



    }
    void displayProfile() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url= URL.displayprofile+id;



        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.w("res", response);

                        UserLoginData userLoginData = new Gson().fromJson(response, UserLoginData.class);
                        if (userLoginData.isSuccess()) {

                            fnameEditText.setText(userLoginData.getData().user.fname);
                            lnameEditText.setText(userLoginData.getData().user.lname);
                            emailEditText.setText(userLoginData.getData().user.mail);
                            passwordEditText.setText(userLoginData.getData().user.pass);
                            rePasswordEditText.setText(userLoginData.getData().user.pass);
//                            phoneEditText .setText(userLoginData.getData().user.phone);
//                           ageEditText.setText(userLoginData.getData().user.age);




                            Arrays.sort( userLoginData.getData().getDisease_ids());
                            temp  = userLoginData;
                            if(userLoginData.getData().names.length > 0){
                                if( userLoginData.getData().names.length == 1 ){
                                    des1.setText(String.valueOf(userLoginData.getData().names[0]));
                                }
                                if( userLoginData.getData().names.length == 2  ){
                                    des1.setText(String.valueOf(userLoginData.getData().names[0]));
                                    des2.setText(String.valueOf(userLoginData.getData().names[1]));
                                }
                                if( userLoginData.getData().names.length  == 3   ){
                                    des1.setText(String.valueOf(userLoginData.getData().names[0]));
                                    des2.setText(String.valueOf(userLoginData.getData().names[1]));
                                    des3.setText(String.valueOf(userLoginData.getData().names[2]));
                                }
                            }
                        } else {
                            Toast.makeText(ProfileActivity.this, getString(R.string.wrong_password), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfileActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();

            }
        }) {

        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    void updateProfile() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url= URL.updateprofile+id;

        for (int i = 0  ; i < temp.getData().getDisease_ids().length ; i++){
            if(i==0){
                new_ids =   temp.getData().getDisease_ids()[0]+"";
            }else{
                new_ids =  new_ids  + "," + temp.getData().getDisease_ids()[i];

            }
        }

        // news_ids = "1,2,3"



        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        UserLoginData userLoginData = new Gson().fromJson(response, UserLoginData.class);
                        if (userLoginData.isSuccess()) {

                            Toast.makeText(ProfileActivity.this, getString(R.string.data_saved)
                                    , Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ProfileActivity.this, getString(R.string.cannot_save)
                                    , Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfileActivity.this, getString(R.string.network_error)
                        , Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("mail",email);
                params.put("pass",password);
                params.put("fname", fname);
                params.put("lname", lname);
                params.put("phone", phone);
                params.put("age", age);
                params.put("d_ids", new_ids);
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public boolean validate() {
        boolean valid = true;

        if (fname.isEmpty() || fname.length() < 3) {
            fnameEditText.setError(getString(R.string.invalid_fname));
            valid = false;
        } else {
            fnameEditText.setError(null);
        }
        if (lname.isEmpty() || lname.length() < 3) {
            lnameEditText.setError(getString(R.string.invalid_lname));
            valid = false;
        } else {
            lnameEditText.setError(null);
        }
        if (phone.isEmpty() || phone.length() != 11) {
            phoneEditText.setError(getString(R.string.invalid_phone));
            valid = false;
        } else {
            phoneEditText.setError(null);
        }
        if (age.isEmpty() || age.length() > 2) {
            ageEditText.setError(getString(R.string.invalid_age));
            valid = false;
        } else {
            ageEditText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError(getString(R.string.invalid_email));
            valid = false;
        }else {
            emailEditText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {

            passwordEditText.setError(getString(R.string.invalid_password));
            valid = false;
        } else {
            passwordEditText.setError(null);
        }

        if (repassword.isEmpty() || !(repassword.equals(password))) {
            rePasswordEditText.setError(getString(R.string.invalid_confirm_password));
            valid = false;
        } else {
            rePasswordEditText.setError(null);
        }

        return valid;
    }

}
