package com.example.doha_.icare.activates;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doha_.icare.Model.Medicine;
import com.example.doha_.icare.Model.UserLoginData;
import com.example.doha_.icare.R;
import com.example.doha_.icare.fragments.AboutFragment;
import com.example.doha_.icare.fragments.FqFragment;
import com.example.doha_.icare.fragments.MasterFragment;
import com.example.doha_.icare.fragments.SymptomsFragment;
import com.example.doha_.icare.utilities.PrefManager;
import com.example.doha_.icare.utilities.URL;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView = null;
    Toolbar toolbar = null;
    //    private static final String[] COUNTRIES = new String[] { "Belgium",
//            "France", "France_", "Italy", "Germany", "Spain" };
    ArrayList<String> names = new ArrayList<>();
    Medicine med;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set the fragment initially
        MasterFragment fragment = new MasterFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        search();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        //How to change elements in the header programatically
        View headerView = navigationView.getHeaderView(0);
        TextView emailText = (TextView) headerView.findViewById(R.id.email);
        emailText.setText("Asmaa@email.com");

        navigationView.setNavigationItemSelectedListener(this);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        // actionBar.setDisplayShowTitleEnabled(false);
        // actionBar.setIcon(R.drawable.ic_action_search);

        LayoutInflater inflator = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.action_bar, null);

        actionBar.setCustomView(v);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, names);
        AutoCompleteTextView textView = (AutoCompleteTextView) v
                .findViewById(R.id.editText1);
        textView.setAdapter(adapter);
        textView.setThreshold(1);
        textView.setHint("Search");
        textView.setHintTextColor(Color.LTGRAY);

//
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedMedicineName = String.valueOf(parent.getItemAtPosition(position));
                String selectedMedicineDescription = "";
                Log.w("selectedid", String.valueOf(parent.getItemAtPosition(position)));

                for (Medicine.Data data : med.getData()) {
                    if (data.getName().equals(selectedMedicineName)) {
                        selectedMedicineDescription = data.getDesc();
                    }
                }

                Intent intent = new Intent(getApplicationContext(), SearchResults.class);
                intent.putExtra("description", selectedMedicineDescription);
                startActivity(intent);

//                id_medecine = Integer.parseInt(medicenes.get(position).getId());
//                getdesc(id_medecine);

            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            //Set the fragment initially
            startFragment(new MasterFragment());

        } else if (id == R.id.about) {
            startFragment(new AboutFragment());

        } else if (id == R.id.profile) {
            Intent intent = new Intent(this,ProfileActivity.class);
            startActivity(intent);

        } else if (id == R.id.symptoms) {
            startFragment(new SymptomsFragment());

        } else if (id == R.id.FQ) {
            startFragment(new FqFragment());

        } else if (id == R.id.logout) {
            UserLoginData   userLoginData
                     =  null;
            PrefManager  prefManager  = new PrefManager(this);
            prefManager.saveUserDataLogin(userLoginData);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public  void startFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();

    }

    public void search() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = URL.search_medecine;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.w("response", response);
                     Toast.makeText(getApplicationContext(), "data loaded." +
                             "", Toast.LENGTH_SHORT).show();

                try {
                    med = new Gson().fromJson(response, Medicine.class);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }


                if (med.getSuccess()) {
                    for (Medicine.Data m : med.getData()) {
                        names.add(m.getName());
                        adapter.notifyDataSetChanged();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Sorry", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Sorry", Toast.LENGTH_SHORT).show();

            }
        });


        queue.add(stringRequest);

    }
}
