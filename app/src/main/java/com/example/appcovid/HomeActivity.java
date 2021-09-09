package com.example.appcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
            switch (item.getItemId()){
                case R.id.bnav_home_fragment:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_frag_container, HomeFragment.newInstance())
                            .commit();
                    return true;

                case R.id.bnav_health_fragment:
                    return true;

                case R.id.bnav_feedback_fragment:
                    return true;

                case R.id.bnav_category_fragment:
                    return true;

                default:
                    return false;
            }
        });
    }
}