package com.example.appcovid.feedback_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appcovid.R;


public class ContactReportFragment extends Fragment {
    public ContactReportFragment() {
        // Required empty public constructor
    }

    public static ContactReportFragment newInstance() {
        ContactReportFragment fragment = new ContactReportFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_report, container, false);
    }
}