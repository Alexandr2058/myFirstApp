package com.example.myapplicationdia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PassFragment extends Fragment {

    private Singlton singlton;
    TextView tvNamePassport, tvCustSNPass, tvCustLNPass, tvInnPass, tvBData;
    String custNamePass ="";
    String custSNPass = "";
    String custLNPass = "";
    int innPass;
    int bData;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pass, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        singlton = Singlton.getInstance();
        tvNamePassport = view.findViewById(R.id.tvNamePass);
        tvCustSNPass = view.findViewById(R.id.tvSecondNamePass);
        tvCustLNPass = view.findViewById(R.id.tvLastNamePass);
        tvInnPass = view.findViewById(R.id.tvInnPass);
        tvBData = view.findViewById(R.id.tvBornDataPass);


        custNamePass = singlton.getName();
        tvNamePassport.setText(custNamePass);

        custSNPass = singlton.getSecondName();
        tvCustSNPass.setText(custSNPass);

        custLNPass = singlton.getLastName();
        tvCustLNPass.setText(custLNPass);

        int custInn = singlton.getInn();
        String s = String.valueOf(custInn);
        tvInnPass.setText(s);

        String custBD = singlton.getBornData();
        String bd = String.valueOf(custBD);
        tvBData.setText(bd);

    }
}