package com.example.doha_.icare.activates;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
import com.example.doha_.icare.Model.UserLoginData;
import com.example.doha_.icare.utilities.PrefManager;
import com.example.doha_.icare.R;
import com.example.doha_.icare.utilities.URL;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    String email,password;

    Button buttonLogin;

    TextView register;

    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefManager = new PrefManager(this);

       /* if (((SharedPreferences) getApplication()).getSession().getId() != 0) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }*/

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        register = (TextView) findViewById(R.id.register);

        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();

                if (!validate()) {
                    return;
                }

                //Write Here your function

                login();
            }
        });


        register.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });

    }


    void login() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url= URL.login;
        //String url = "http://192.168.0.102/Icare/public/api/login";

        final ProgressDialog loading = ProgressDialog.show(this, null, "Please wait...", false, false);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.w("response", response);
                        loading.dismiss();

                        UserLoginData userLoginData = new Gson().fromJson(response, UserLoginData.class);
                        if (userLoginData.isSuccess()) {
                            prefManager.saveUserDataLogin(userLoginData);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "wrong user or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(LoginActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                Log.w("response", "hat didn't work!");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("mail", emailEditText.getText().toString().trim());
                params.put("pass", passwordEditText.getText().toString().trim());

                return params;

            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    public boolean validate() {
        boolean valid = true;

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


        return valid;
    }

}


