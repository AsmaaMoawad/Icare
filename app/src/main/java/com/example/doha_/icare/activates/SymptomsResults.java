package com.example.doha_.icare.activates;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doha_.icare.Model.Medicine_user;
import com.example.doha_.icare.Model.SymptomsDeseaseResponse;
import com.example.doha_.icare.R;

import java.util.ArrayList;

public class SymptomsResults extends AppCompatActivity {

    TextView result,drug_view;

    ArrayList<SymptomsDeseaseResponse.Data> dataArrayList;
    ArrayList<Medicine_user.Data> drugArrayList;
    Button More_discrib_med_button;
    ArrayList<String>describtion_med=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms_results);
        result = (TextView) findViewById(R.id.result);
        drug_view=(TextView)findViewById(R.id.drugview);
        More_discrib_med_button=(Button)findViewById(R.id.more);
//        String ids=getIntent().getStringExtra("data_ids");
//        Toast.makeText(this,ids,Toast.LENGTH_LONG).show();
        dataArrayList  = getIntent().getParcelableArrayListExtra("data");
        drugArrayList  = (ArrayList<Medicine_user.Data>) getIntent().getSerializableExtra("drug");




        for(int i = 0 ; i  < dataArrayList.size() ; i++){
            result.append(dataArrayList.get(i).d_name+"\n");
        }
        for(int i = 0 ; i  < drugArrayList.size() ; i++){
            drug_view.append(drugArrayList.get(i).getName()+"\n");

        }
        for(int i = 0 ; i  < drugArrayList.size() ; i++){
            describtion_med.add( drugArrayList.get(i).getDesc());

        }
        More_discrib_med_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(SymptomsResults.this,Descriotion_Med.class);
                intent.putStringArrayListExtra("description",describtion_med);
                intent.putExtra("drugList",drugArrayList);
                startActivity(intent);

            }
        });



}




}
