package com.example.appcovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.appcovid.feedback_fragment.FeedbackBaseFragment;
import com.example.appcovid.home_fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupBottomNav();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frag_container, HomeFragment.newInstance())
                .commit();
    }

    private void setupBottomNav()
    {
        bottomNav = findViewById(R.id.main_bottom_nav);
        bottomNav.setOnItemSelectedListener((item) -> {
            Fragment selectFragment = null;
            switch (item.getItemId()){
                case R.id.bnav_home_fragment:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_frag_container, HomeFragment.newInstance())
                            .commit();
                    return true;

                case R.id.bnav_health_fragment:
                    return true;

                case R.id.bnav_feedback_fragment:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_frag_container, FeedbackBaseFragment.newInstance())
                            .commit();
                    return true;

                case R.id.bnav_category_fragment:
                    /*getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_frag_container, category_fragment.newInstance())
                            .commit();*/
                    return true;

                default:
                    return false;
            }
        });
    }
}