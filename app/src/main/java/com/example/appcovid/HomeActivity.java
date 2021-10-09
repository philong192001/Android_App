package com.example.appcovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.appcovid.feedback_fragment.FeedbackBaseFragment;
import com.example.appcovid.home_fragment.HomeFragment;
import com.example.appcovid.network.dto.CreateAccDto;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    private FloatingActionButton fabQr;
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

        fabQr = findViewById(R.id.add_transaction_fab);

        fabQr.setOnClickListener(v -> {
            /**Qr code**/
            Toast.makeText(HomeActivity.this, "Qrrr", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(HomeActivity.this, QRCodeActivity.class);
            intent.putExtra("accinfo", acc);
            startActivity(intent);
            finish();
        });

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
                            .replace(R.id.main_frag_container, category_fragment.newInstance(acc))
                            .commit();
                    return true;
//                case R.id.add_transaction_fab:
//                    Log.d("INFO", "ABC");
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.main_frag_container,health_fragment.newInstance(acc));

                default:
                    return false;
            }
        });
    }

    public void getQRFragment(){
        Log.d("INFO", "ABC");
        getSupportFragmentManager().beginTransaction()
                           .replace(R.id.main_frag_container,health_fragment.newInstance());
    }
}