package com.example.doha_.icare.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.doha_.icare.Model.Medicine_user;
import com.example.doha_.icare.Model.Symptom;
import com.example.doha_.icare.Model.SymptomsDeseaseResponse;
import com.example.doha_.icare.R;
import com.example.doha_.icare.activates.SymptomsResults;
import com.example.doha_.icare.utilities.SharedPreferences;
import com.example.doha_.icare.utilities.URL;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Asmaa on 28-Jun-17.
 */

public class SymptomsFragment extends Fragment {
    Spinner spinner;

    int x = 1;
    Intent i;
    TextView S1, S2, S3, S4, S5;
    String Sy1_str, Sy2_str, Sy3_str, Sy4_str, Sy5_str;
    List<Integer> integerList = new ArrayList<>();
    ArrayList<String> Symptoms = new ArrayList<>();
    ArrayList<String> ids_diseaseslist = new ArrayList<>();

    ArrayAdapter<CharSequence> adapter;
    Symptom Sym;
    String[] no_replicate = new String[5];
    String[] ids = new String[5];
    Intent intent;
    String attemp;
    Button submit;
    Button reset;
    int id;
    boolean userIsInteracting = false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        id = ((SharedPreferences) getActivity().getApplication()).getSession().getId();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_symptoms, container, false);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        S1 = (TextView) view.findViewById(R.id.sym1);
        S2 = (TextView) view.findViewById(R.id.sym2);
        S3 = (TextView) view.findViewById(R.id.sym3);
        S4 = (TextView) view.findViewById(R.id.sym4);
        S5 = (TextView) view.findViewById(R.id.sym5);
        submit = (Button) view.findViewById(R.id.Intent);
        reset = (Button) view.findViewById(R.id.reset);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sy1_str = S1.getText().toString();
                Sy2_str = S2.getText().toString();
                Sy3_str = S3.getText().toString();
                Sy4_str = S4.getText().toString();
                Sy5_str = S5.getText().toString();

                if (Sy1_str.equals("") || Sy2_str.equals("") || Sy3_str.equals("") || Sy4_str.equals("") || Sy5_str.equals("")) {
                    Toast.makeText(getContext(), "Please  Complete the data ", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getContext(), "loading ", Toast.LENGTH_LONG).show();



                    results();
                    medecine();

                }

            }
        });

        listSymptom();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (userIsInteracting) {
                    if (x <= 5) {
                        switch (x) {
                            case 1:
                                S1.setText(spinner.getSelectedItem().toString());
                                Sy1_str = S1.getText().toString();
                                no_replicate[0] = Sy1_str;
                                ids[0] = integerList.get(position) + "";
                                x++;
                                break;
                            case 2:
                                attemp = parent.getItemAtPosition(position).toString();
                                if (attemp.equals(no_replicate[0])) {
                                    Toast.makeText(getContext(), "Sorry This item you  Choosed before  ", Toast.LENGTH_LONG).show();
                                } else {
                                    S2.setText(parent.getItemAtPosition(position).toString());
                                    Sy2_str = S2.getText().toString();
                                    no_replicate[1] = Sy2_str;
                                    ids[1] = integerList.get(position) + "";

                                    x++;
                                }
                                break;
                            case 3:
                                attemp = parent.getItemAtPosition(position).toString();
                                if (attemp.equals(no_replicate[0]) || attemp.equals(no_replicate[1])) {
                                    Toast.makeText(getContext(), "Sorry This item you  Choosed before  ", Toast.LENGTH_LONG).show();
                                } else {
                                    S3.setText(parent.getItemAtPosition(position).toString());
                                    Sy3_str = S3.getText().toString();
                                    no_replicate[2] = Sy2_str;
                                    ids[2] = integerList.get(position) + "";

                                    x++;

                                }
                                break;
                            case 4:
                                attemp = parent.getItemAtPosition(position).toString();
                                if (attemp.equals(no_replicate[0]) || attemp.equals(no_replicate[1]) || attemp.equals(attemp.equals(no_replicate[2]))) {
                                    Toast.makeText(getContext(), "Sorry This item you  Choosed before  ", Toast.LENGTH_LONG).show();
                                } else {
                                    S4.setText(parent.getItemAtPosition(position).toString());
                                    Sy4_str = S4.getText().toString();
                                    no_replicate[3] = Sy4_str;
                                    ids[3] = integerList.get(position) + "";

                                    x++;

                                }
                                break;
                            case 5:
                                attemp = parent.getItemAtPosition(position).toString();
                                if (attemp.equals(no_replicate[0]) || attemp.equals(no_replicate[1]) || attemp.equals(attemp.equals(no_replicate[2])) || attemp.equals(no_replicate[3])) {
                                    Toast.makeText(getContext(), "Sorry This item you  Choosed before  ", Toast.LENGTH_LONG).show();
                                } else {
                                    S5.setText(parent.getItemAtPosition(position).toString());
                                    Sy5_str = S5.getText().toString();
                                    no_replicate[4] = Sy5_str;
                                    ids[4] = integerList.get(position) + "";

                                    x++;

                                }
                                break;
                        }
                    } else {
                        Toast.makeText(getContext(), "NO MORE  ", Toast.LENGTH_LONG).show();


                    }

                } else {
                    userIsInteracting = true;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no_replicate = new String[5];
                ids = new String[5];
                x = 1;
                S1.setText("");
                S2.setText("");
                S3.setText("");
                S4.setText("");
                S5.setText("");

            }
        });


        return view;
    }

    public void listSymptom() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = URL.GETSymptoms;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Sym = new Gson().fromJson(response, Symptom.class);
                if (Sym.isSuccess()) {

                    for (Symptom.Data m : Sym.getData()) {
                        Symptoms.add(m.name);
                        integerList.add(m.id);
                    }
                    adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, Symptoms);
                    spinner.setAdapter(adapter);


                } else {
                    Toast.makeText(getContext(), "Sorry", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Sorry", Toast.LENGTH_SHORT).show();

            }
        });

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        queue.add(stringRequest);


    }

    public void results() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = URL.getSymptomsDesease;
        final String p_ids = this.ids[0] + "," + this.ids[1] + "," + this.ids[2] + "," + this.ids[3] + "," + this.ids[4];

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                SymptomsDeseaseResponse Sym = new Gson().fromJson(response, SymptomsDeseaseResponse.class);
                if (Sym.success) {


                    i = new Intent(getContext(), SymptomsResults.class);
                    i.putParcelableArrayListExtra("data", Sym.data);
//                    startActivity(i);


                } else {
                    Toast.makeText(getContext(), "Sorry", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Sorry", Toast.LENGTH_SHORT).show();

            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("symptoms", p_ids);

                return params;

            }
        };

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        queue.add(stringRequest);


    }

    public void medecine() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = URL.getmedecin + id;
        final String diseases_ids = this.ids[0] + "," + this.ids[1]+ "," + this.ids[2]+ "," + this.ids[3]+ "," + this.ids[4];
        for (int i=0;i<5;i++) {
            ids_diseaseslist.add(ids[i]);
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Medicine_user med = new Gson().fromJson(response, Medicine_user.class);
                if (med.success) {
                    Log.d("MED", response);


//                    for (Medicine_user.Data m : med.getData()) {
//
//
//
//                    }

//                    Intent i = new Intent(getContext(), SymptomsResults.class);
                    if(i!=null){
                        i.putExtra("drug", (Serializable) med.getData());

                        startActivity(i);
                    }


//                    Intent i  = new Intent(getContext(),Frag_Symtoms.class);
//                    i.putExtra("data_ids",p_ids);
//                    startActivity(i);


                } else {
                    Toast.makeText(getContext(), "error in medicine", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Sorry", Toast.LENGTH_SHORT).show();

            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("d_id", diseases_ids);

                return params;

            }
        };

        queue.add(stringRequest);


    }


//   @Override
//    public void onUserInteraction() {
//        super.onUserInteraction();
//        userIsInteracting = true;
//    }


}
