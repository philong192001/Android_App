package com.example.appcovid.feedback_fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appcovid.HomeActivity;
import com.example.appcovid.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.security.InvalidParameterException;


public class FeedbackBaseFragment extends Fragment {

    private FragmentStateAdapter pagerAdapter;
    private TabLayout tabs;
    private ViewPager2 viewPager;

    public FeedbackBaseFragment() {

    }

    public static FeedbackBaseFragment newInstance() {
        FeedbackBaseFragment fragment = new FeedbackBaseFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feedback_base, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pagerAdapter = new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                if(position == 0 ) return FeedbackFragment.newInstance();
                else if(position == 1) return ContactReportFragment.newInstance();
                else throw new InvalidParameterException();
            }

            @Override
            public int getItemCount() {
                return 2;
            }
        };

        tabs = view.findViewById(R.id.feedback_base_tabs);
        viewPager = view.findViewById(R.id.feedback_base_viewpager);
        viewPager.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabs, viewPager, (tab, position) -> {
            if(position == 0 ) tab.setText("Phản ánh");
            else if(position == 1) tab.setText("Khai báo tiếp xúc");
        }).attach();


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((HomeActivity) getActivity()).getSupportActionBar().setTitle("Phản ánh thông tin");

    }
}















