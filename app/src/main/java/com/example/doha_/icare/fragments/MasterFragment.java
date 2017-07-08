package com.example.doha_.icare.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.doha_.icare.R;
import com.example.doha_.icare.activates.MainActivity;

/**
 * Created by Asmaa on 28-Jun-17.
 */

public class MasterFragment extends Fragment {
    Button about,FQ;

    Button descButton;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_master, container, false);

        descButton = (Button) view.findViewById(R.id.buttonDescrip);
        about=(Button)view.findViewById(R.id.about);
        FQ=(Button)view.findViewById(R.id.faq);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).startFragment(new AboutFragment());

            }
        });
        FQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity)getActivity()).startFragment(new FqFragment());
            }
        });
        descButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set the fragment initially
                ((MainActivity)getActivity()).startFragment(new SymptomsFragment());

            }
        });
        return view;

    }
}
