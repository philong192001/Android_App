package com.example.appcovid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ScreenOTPActivity extends AppCompatActivity {

    private String phone = "";
    private EditText otpText;
    private Button confirmOtpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_otp);

        phone = getIntent().getStringExtra("phone");

        otpText = findViewById(R.id.et_otp);
        confirmOtpBtn = findViewById(R.id.btn_confirm_otp);
        confirmOtpBtn.setOnClickListener( v -> confirmOtp());

    }

    private void createOtp()
    {
        //call api create otp
    }

    private void confirmOtp()
    {
        String otp = otpText.getText().toString();

        if(otp.isEmpty())
        {
            Toast.makeText(this,"Nhập mã OTP", Toast.LENGTH_LONG).show();
            return;
        }

        //call api to confirm otp
        Intent intent = new Intent(this, DeclarePersonalInfoActivity.class);
        intent.putExtra("phone", phone);
        startActivity(intent);
        finish();
    }
}
