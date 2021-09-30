package com.example.appcovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] permissions = new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btVerificationPhone = findViewById(R.id.bt_VerificationPhone);
        EditText edPhone = findViewById(R.id.ed_phone);


        btVerificationPhone.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ScreenOTPActivity.class);

            String phone = edPhone.getText().toString();
            if(phone.isEmpty()){
                Toast.makeText(getApplicationContext(), "Nhập số điện thoại", Toast.LENGTH_LONG).show();
                return;
            }

            intent.putExtra("phone", phone);
            startActivity(intent);
            finish();
        });

        if(needGrantPermission())
            ActivityCompat.requestPermissions(this, permissions, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                } else {

                }
                return;
        }
        // Other 'case' lines to check for other
        // permissions this app might request.
    }


    private boolean needGrantPermission()
    {
        boolean r = false;
        for(String p: permissions)
        {
            if(ContextCompat.checkSelfPermission(this,p) != PackageManager.PERMISSION_GRANTED)
            {
                r = true;
                break;
            }
        }

        return r;
    }
}