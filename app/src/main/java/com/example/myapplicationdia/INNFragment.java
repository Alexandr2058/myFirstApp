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

public class INNFragment extends Fragment {

    private Singlton singlton;
    TextView tvNI, tvSNI, tvLNI, tvBDI, tvII;
    String nameInn = "";
    String sNInn = "";
    String lNInn = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_i_n_n, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        singlton = Singlton.getInstance();
        tvNI = view.findViewById(R.id.tvNameI);
        tvSNI = view.findViewById(R.id.tvSecondNameI);
        tvLNI = view.findViewById(R.id.tvLastNameI);
        tvBDI = view.findViewById(R.id.tvBornDataI);
        tvII = view.findViewById(R.id.tvInnIn);

        nameInn = singlton.getName();
        tvNI.setText(nameInn);

        sNInn = singlton.getSecondName();
        tvSNI.setText(sNInn);

        lNInn = singlton.getLastName();
        tvLNI.setText(lNInn);

        String bdI = singlton.getBornData();
        String bDateI = String.valueOf(bdI);
        tvBDI.setText(bDateI);

        int inn = singlton.getInn();
        String iI = String.valueOf(inn);
        tvII.setText(iI);
        Log.d ("MyLog", "inn");

    }
}