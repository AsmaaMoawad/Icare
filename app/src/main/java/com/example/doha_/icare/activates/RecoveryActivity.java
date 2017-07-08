package com.example.doha_.icare.activates;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.doha_.icare.R;
import com.example.doha_.icare.utilities.URL;

import java.util.HashMap;
import java.util.Map;

public class RecoveryActivity extends AppCompatActivity {

    EditText emailEditText;
    String email;
    Button sendButton;
    TextView loginTextView,registerTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);
        emailEditText = (EditText) findViewById(R.id.emailEditText);

        loginTextView = (TextView) findViewById(R.id.loginTextView);
        registerTextView = (TextView) findViewById(R.id.registerTextView);

        sendButton = (Button) findViewById(R.id.buttonSend);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailEditText.getText().toString();
                if (!validate()) {
                    return;
                }
                recovery();
            }

        });

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecoveryActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecoveryActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void recovery() {
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = URL.recovery;

            final ProgressDialog loading = ProgressDialog.show(this,
                    null, getString(R.string.please_wait),
                    false, false);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    loading.dismiss();
                    Toast.makeText(RecoveryActivity.this,
                            getString(R.string.network_error)
                            , Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("mail", emailEditText.getText().toString());
                    return params;
                }
            };

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

        return valid;
    }


}
