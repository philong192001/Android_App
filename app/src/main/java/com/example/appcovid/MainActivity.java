package com.example.appcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
    }
}