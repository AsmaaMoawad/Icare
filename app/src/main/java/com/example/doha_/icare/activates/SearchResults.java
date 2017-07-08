package com.example.doha_.icare.activates;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.doha_.icare.R;

public class SearchResults extends AppCompatActivity {
    TextView DES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        DES=(TextView)findViewById(R.id.description);
        DES.setText(this.getIntent().getExtras().getString("description"));
    }
}
