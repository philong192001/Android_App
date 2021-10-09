package com.example.appcovid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appcovid.network.dto.CreateAccDto;


public class category_fragment extends Fragment {

    Button category;
    Button Proviso;
    Button logout;
    CreateAccDto dto = new CreateAccDto();
    public category_fragment() {

    }
    public static category_fragment newInstance(CreateAccDto acc) {
        category_fragment fragment = new category_fragment();
        Bundle args = new Bundle();
        args.putSerializable("accinfo", acc);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dto = ((CreateAccDto) getArguments().getSerializable("accinfo"));

            Log.d("adsafdABC",dto.toString());

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        category = view.findViewById(R.id.button);
        Proviso = view.findViewById(R.id.button3);
        logout = view.findViewById(R.id.logout);

        logout.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        });
        Proviso.setOnClickListener(v ->{
            Intent intent = new Intent(getActivity(), ProvisoActivity.class);
            startActivity(intent);
        } );

        category.setOnClickListener(v ->{
            Intent intent = new Intent(getActivity(), InfomationUserActivity.class);
            intent.putExtra("info",dto);
            startActivity(intent);
        } );
    }
}

