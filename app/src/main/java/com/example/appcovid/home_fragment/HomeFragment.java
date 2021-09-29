package com.example.appcovid.home_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.appcovid.DeclarePersonalInfoActivity;
import com.example.appcovid.R;
import com.example.appcovid.network.dto.CreateAccDto;
import com.google.android.material.chip.Chip;


public class HomeFragment extends Fragment {

    private TextView detailText;
    private Button btnDeclare;
    private CreateAccDto acc;

    private Chip chipVn;
    private Chip chipWorld;

    private TextView txtInfected;
    private TextView txtDeath;
    private TextView txtRecovered;

    private TextView txtInfectedInc;
    private TextView txtDeathInc;
    private TextView txtRecoveredInc;

    public HomeFragment() {

    }

    public static HomeFragment newInstance(CreateAccDto acc) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putSerializable("accinfo", acc);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            acc = ((CreateAccDto) getArguments().getSerializable("accinfo"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnDeclare = view.findViewById(R.id.btn_declare_info);
        btnDeclare.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), DeclarePersonalInfoActivity.class);
            intent.putExtra("accinfo", acc);
            startActivity(intent);
        });

        chipVn = (Chip) view.findViewById(R.id.chip_vn);
        chipWorld = (Chip) view.findViewById(R.id.chip_world);

        txtInfected = (TextView) view.findViewById(R.id.txt_infected);
        txtDeath = (TextView) view.findViewById(R.id.txt_death);
        txtRecovered = (TextView) view.findViewById(R.id.txt_recovered);
        txtInfectedInc = (TextView) view.findViewById(R.id.txt_infected_inc);
        txtDeathInc = (TextView) view.findViewById(R.id.txt_death_inc);
        txtRecoveredInc = (TextView) view.findViewById(R.id.txt_recovered_inc);


        chipVn.setOnClickListener(v -> {
            txtInfected.setText("563,677");
            txtDeath.setText("14,135");
            txtRecovered.setText("325,674");
            txtInfectedInc.setText("14,435");
            txtDeathInc.setText("362");
            txtRecoveredInc.setText("15,234");
        });

        chipWorld.setOnClickListener(v -> {
            txtInfected.setText("219,563,677");
            txtDeath.setText("4,551,262");
            txtRecovered.setText("210,533,555");
            txtInfectedInc.setText("175,090");
            txtDeathInc.setText("3,702");
            txtRecoveredInc.setText("194,185");
        });
    }


}