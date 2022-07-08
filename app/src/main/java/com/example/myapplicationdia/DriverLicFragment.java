package com.example.myapplicationdia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DriverLicFragment extends Fragment {

    private Singlton singlton;
    TextView tvNameDL, tvSNDL, tvLNDL;
    String custNameDL = "";
    String custSNDL = "";
    String custLNDL = "";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_driver_lic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        singlton = Singlton.getInstance();
        tvNameDL = view.findViewById(R.id.tvNameDL);
        tvSNDL = view.findViewById(R.id.tvSecondNameDL);
        tvLNDL = view.findViewById(R.id.tvLastNameDL);

        custNameDL = singlton.getName();
        tvNameDL.setText(custNameDL);

        custSNDL = singlton.getSecondName();
        tvSNDL.setText(custSNDL);

        custLNDL = singlton.getLastName();
        tvLNDL.setText(custLNDL);

    }
}