package com.example.doha_.icare.activates;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
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
import com.example.doha_.icare.Model.SearchMapResponse;
import com.example.doha_.icare.Model.User;
import com.example.doha_.icare.R;
import com.example.doha_.icare.utilities.URL;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
        , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

//    BitmapDescriptor icon_pharmacy=BitmapDescriptorFactory.fromResource(R.drawable.ic_map_pharmacy);
//    BitmapDescriptor icon_hospital=BitmapDescriptorFactory.fromResource(R.drawable.ic_map_hospital);

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    double myLatitude, myLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        MapsInitializer.initialize(getApplicationContext());

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            myLatitude = mLastLocation.getLatitude();
            myLongitude = mLastLocation.getLongitude();

            stringRequest();

            LatLng myLocation = new LatLng(myLatitude, myLongitude);
            mMap.addMarker(new MarkerOptions().position(myLocation).title("My Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(myLocation).zoom(9).build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            Log.w("latitude", String.valueOf(mLastLocation.getLatitude()));
            Log.w("longitude", String.valueOf(mLastLocation.getLongitude()));

            listSymptom("hospital",String.valueOf(mLastLocation.getLatitude())+","+String.valueOf(mLastLocation.getLongitude()));
            listSymptom("pharmacy",String.valueOf(mLastLocation.getLatitude())+","+String.valueOf(mLastLocation.getLongitude()));        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    void stringRequest() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.0.103/PlaneEye/v1/nearestFriends";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.w("response", response);

                        User user = new Gson().fromJson(response, User.class);

                        drowNearestUsersOnMap(user);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w("response", "hat didn't work!");

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

//                params.put("email", emailEditText.getText().toString());
//                params.put("password", passwordEditText.getText().toString());

                params.put("user_id", "1");

                if (mLastLocation != null) {
                    params.put("latitude", String.valueOf(myLatitude));
                    params.put("longitude", String.valueOf(myLongitude));
                }

                params.put("currentTime", "12");

                return params;

            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void drowNearestUsersOnMap(User user) {
        for (User.Users u : user.getUsers()) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(u.getLatitude(), u.getLongitude()))
                    .title(u.getFname())
                    .snippet(u.getFname() + " " + u.getLastTime()));
//                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        }
    }



    public void listSymptom(final String  type,String location) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = URL.map_url+"location="+location+"&radius=2000&type="+type+"&key="+URL.map_key;
        //String url =  BASE_URL.concat("getSymptoms");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

             //   .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                SearchMapResponse   searchMapResponse = new Gson().fromJson(response, SearchMapResponse.class);
                if (searchMapResponse.status.equals("OK")) {


                    if(type.equals("hospital")){
                        for (int  i = 0 ; i < searchMapResponse.results.size() ; i++) {
                            mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(searchMapResponse.results.get(i).geometry.location.lat, searchMapResponse.results.get(i).geometry.location.lng))
                                    .title(searchMapResponse.results.get(i).name)
                                    .snippet(type).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_hospital)));
                        }
                    }else{
                        for (int  i = 0 ; i < searchMapResponse.results.size() ; i++) {
                            mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(searchMapResponse.results.get(i).geometry.location.lat, searchMapResponse.results.get(i).geometry.location.lng))
                                    .title(searchMapResponse.results.get(i).name)
                                    .snippet(type)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_pharmacy)));
                        }
                    }



                } else {
                    Toast.makeText(MapsActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MapsActivity.this, "Sorry", Toast.LENGTH_SHORT).show();

            }
        });

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        queue.add(stringRequest);


    }

}
