package com.example.appcovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.appcovid.feedback_fragment.FeedbackBaseFragment;
import com.example.appcovid.home_fragment.HomeFragment;
import com.example.appcovid.network.dto.CreateAccDto;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    private FloatingActionButton fabQr;
    private CreateAccDto acc;
    private String phoneSaveFile;

    private String filename = "LoginSession.txt";
    private String filepath = "AppCovidDataLogin";
    File myInternalFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ContextWrapper contextWrapper = new ContextWrapper(
                getApplicationContext());
        File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
        myInternalFile = new File(directory, filename);

        acc = ((CreateAccDto) getIntent().getSerializableExtra("accinfo"));

        Log.d("INFOUSER",acc.toString());

        setupBottomNav();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frag_container, HomeFragment.newInstance(acc))
                .commit();

        fabQr = findViewById(R.id.add_transaction_fab);

        fabQr.setOnClickListener(v -> {
            /**Qr code**/
            Toast.makeText(HomeActivity.this, "QRCode", Toast.LENGTH_LONG).show();
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

                default:
                    return false;
            }
        });
    }

//    public void saveDataDefault(String phone){
//        String fileName = "saveDataLogin";
//
//        FileOutputStream outputStream = null;
//        try {
//            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
//            outputStream.write(phone.getBytes(StandardCharsets.UTF_8));
//            outputStream.close();
//            Log.d("Save file" , phone);
//            Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void saveDataLogin(){
        try {
            //Mở file
            FileOutputStream fos = new FileOutputStream(myInternalFile);
            //Ghi dữ liệu vào file
            fos.write(phoneSaveFile.getBytes());

            Log.d("login phone 123",phoneSaveFile);

            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}