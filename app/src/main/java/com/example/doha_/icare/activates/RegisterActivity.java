package com.example.doha_.icare.activates;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doha_.icare.Model.GetDesase_user;
import com.example.doha_.icare.Model.UserRegisterData_;
import com.example.doha_.icare.utilities.PrefManager;
import com.example.doha_.icare.R;
import com.example.doha_.icare.utilities.URL;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import static com.example.doha_.icare.Model.UserRegisterData.setUserDataPrefernces;

public class RegisterActivity extends AppCompatActivity {
    EditText fnameEditText,lnameEditText,phoneEditText,ageEditText,
            emailEditText,passwordEditText,rePasswordEditText;
    String fname,lname,phone,age,email,password,repassword;

    String des1_str,des2_str,des3_str;

    Spinner getdata_spinner;
    Button registerbutton;
    List<Integer> integerList = new ArrayList<>();
    TextView des1;
    TextView des2;
    TextView des3;
    GetDesase_user desase_user;
    ArrayList<String> des_arr = new ArrayList<>();
    ArrayAdapter<CharSequence>adapter;
    boolean userIsInteracting = false;
    int x = 1;
    String [] no_replicate=new String[3];
    String attemp;
    String [] ids=new String[3];
    String str_ids;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        prefManager = new PrefManager(this);

//        if (((MyApplication) getApplication()).getSession().getId() != 0) {
//            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
//        }


        fnameEditText = (EditText) findViewById(R.id.fnameEditText);
        lnameEditText = (EditText) findViewById(R.id.lnameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        ageEditText = (EditText) findViewById(R.id.ageEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        registerbutton = (Button) findViewById(R.id.registerbutton);
        rePasswordEditText = (EditText) findViewById(R.id.rePasswordEditText);

        getdata_spinner=(Spinner)findViewById(R.id.getdes);
        des1 = (TextView) findViewById(R.id.Des1);
        des2 = (TextView) findViewById(R.id.Des2);
        des3 = (TextView) findViewById(R.id.Des3);
        listdes();

        getdata_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if(userIsInteracting){
                    if(x<=3 ) {
                        switch (x) {
                            case 1:
                                des1.setText(parent.getSelectedItem().toString());
                                des1_str = des1.getText().toString();
                                no_replicate[0] = des1_str;
                                ids[0] = integerList.get(position)+"";
                                x++;
                                break;
                            case 2:
                                attemp = parent.getItemAtPosition(position).toString();
                                if (attemp.equals(no_replicate[0])) {
                                    Toast.makeText(getApplicationContext(), "Sorry This item you  Choosed before  ", Toast.LENGTH_LONG).show();
                                } else {
                                   des2.setText(parent.getItemAtPosition(position).toString());
                                    des2_str = des2.getText().toString();
                                    no_replicate[1] = des2_str;
                                    ids[1] = integerList.get(position)+"";

                                    x++;
                                }
                                break;
                            case 3:
                                attemp = parent.getItemAtPosition(position).toString();
                                if (attemp.equals(no_replicate[0]) || attemp.equals(no_replicate[1])) {
                                    Toast.makeText(getApplicationContext(), "Sorry This item you  Choosed before  ", Toast.LENGTH_LONG).show();
                                } else {
                                    des3.setText(parent.getItemAtPosition(position).toString());
                                    des3_str = des3.getText().toString();
                                    no_replicate[2] = des3_str;
                                    ids[2] = integerList.get(position)+"";


                                    x++;

                                }
                                break;

                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "NO MORE  ", Toast.LENGTH_LONG).show();


                    }

                }else{
                    userIsInteracting = true;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        registerbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent); */

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

                registration();


            }
        });


    }
    public void listdes() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url= URL.listcronicdeses;

         str_ids = this.ids[0]+","+this.ids[1]+","+this.ids[2];


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                desase_user = new Gson().fromJson(response, GetDesase_user.class);
                if (desase_user.success) {

                    for (GetDesase_user.Data m : desase_user.data) {
                        des_arr.add(m.name);
                        integerList.add(m.id);

                    }
                    adapter = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, des_arr);
                    getdata_spinner.setAdapter(adapter);


                } else {
                    Toast.makeText(RegisterActivity.this, "Sorry get data", Toast.LENGTH_SHORT).show();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(RegisterActivity.this, "Sorry response data deses", Toast.LENGTH_SHORT).show();

            }
        });

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        queue.add(stringRequest);


    }


    void registration() {
        // Instantiate the RequestQueue.
        str_ids = this.ids[0]+","+this.ids[1]+","+this.ids[2];
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = URL.registration;

        final ProgressDialog loading = ProgressDialog.show(this, null, "Please wait...", false, false);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.w("response", response);
                        loading.dismiss();


                        UserRegisterData_ userRegisterData_ = new Gson().fromJson(response, UserRegisterData_.class);
                        if (userRegisterData_.isSuccess()) {

                            prefManager.saveUserDataRegister(userRegisterData_);
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            Toast.makeText(RegisterActivity.this, "You Are Registered", Toast.LENGTH_SHORT).show();

                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Email taken", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(RegisterActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                Log.w("response", "had didn't work!");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("mail", emailEditText.getText().toString());
                params.put("pass", passwordEditText.getText().toString());
                params.put("fname", fnameEditText.getText().toString());
                params.put("lname", lnameEditText.getText().toString());
                params.put("phone", phoneEditText.getText().toString());
                params.put("age", ageEditText.getText().toString());
                params.put("d_ids",str_ids);

//                params.put("mail", "doa@gmail.com");
//                params.put("pass", "123456");
//                params.put("fname", "ayman");
//                params.put("lname", "ramadan");
//                params.put("phone", "01122334455");
//                params.put("age", "26");
//                params.put("d_ids","1,2,3");


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
