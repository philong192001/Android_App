package com.example.appcovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.appcovid.feedback_fragment.FeedbackBaseFragment;
import com.example.appcovid.home_fragment.HomeFragment;
import com.example.appcovid.network.dto.CreateAccDto;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;

    private CreateAccDto acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        acc = ((CreateAccDto) getIntent().getSerializableExtra("accinfo"));
        setupBottomNav();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frag_container, HomeFragment.newInstance(acc))
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
                            .replace(R.id.main_frag_container, HomeFragment.newInstance(acc))
                            .commit();
                    return true;

                case R.id.bnav_health_fragment:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_frag_container, health_fragment.newInstance())
                            .commit();
                    return true;

                case R.id.bnav_feedback_fragment:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_frag_container, FeedbackBaseFragment.newInstance())
                            .commit();
                    return true;

                case R.id.bnav_category_fragment:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_frag_container, category_fragment.newInstance())
                            .commit();
                    return true;

                default:
                    return false;
            }
        });
    }
}