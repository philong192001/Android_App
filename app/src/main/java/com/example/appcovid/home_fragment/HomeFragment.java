package com.example.appcovid.home_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.appcovid.DeclarePersonalInfoActivity;
import com.example.appcovid.InfoEmp;
import com.example.appcovid.R;
import com.example.appcovid.ScreenOTPActivity;
import com.example.appcovid.network.dto.CreateAccDto;


public class HomeFragment extends Fragment {

    private TextView detailText;
    private Button btnDeclare;
    private CreateAccDto acc;

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
    }
}