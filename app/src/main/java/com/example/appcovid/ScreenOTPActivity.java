package com.example.appcovid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appcovid.network.NetworkModule;
import com.example.appcovid.network.OtpService;
import com.example.appcovid.network.dto.CreateAccDto;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScreenOTPActivity extends AppCompatActivity {

    private String phone = "";
    private EditText otpText;
    private Button confirmOtpBtn;
    private OtpService otpService = NetworkModule.otpService;
    private View loadingOverlay;
    private ProgressBar loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_otp);

        phone = getIntent().getStringExtra("phone");

        otpText = findViewById(R.id.et_otp);
        confirmOtpBtn = findViewById(R.id.btn_confirm_otp);
        confirmOtpBtn.setOnClickListener( v -> confirmOtp());

        loadingOverlay = findViewById(R.id.loading_overlay);
        loadingBar = findViewById(R.id.loading_prgr);

        createOtp();

    }

    private void createOtp()
    {
        /**
         * Call api create otp
         * **/

        setLoadingState(true);

        Map<String, String> body = new HashMap<>();
        body.put("phone", phone);
        Call<Boolean> call = otpService.createOtp(body);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful())
                {
                    boolean success = response.body();
                    if(success)
                    {
                        setLoadingState(false);
                    }
                    else
                    {
                        Toast.makeText(ScreenOTPActivity.this, "Lỗi khi tạo otp", Toast.LENGTH_LONG).show();
                        loadingOverlay.setVisibility(View.INVISIBLE);
                        loadingBar.setVisibility(View.INVISIBLE);
                    }
                }
                else
                {
                    Toast.makeText(ScreenOTPActivity.this, "Lỗi khi tạo otp", Toast.LENGTH_LONG).show();
                    loadingOverlay.setVisibility(View.INVISIBLE);
                    loadingBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(ScreenOTPActivity.this, "Lỗi khi tạo otp", Toast.LENGTH_LONG).show();
                loadingOverlay.setVisibility(View.INVISIBLE);
                loadingBar.setVisibility(View.INVISIBLE);
            }
        });
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

        Map<String, String> body = new HashMap<>();
        body.put("otp", otpText.getText().toString());
        Call<Boolean> call = otpService.confirmOtp(body);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful())
                {
                    boolean success = response.body();
                    if(success)
                    {
                        checkAccExisted();
                    }
                    else
                    {
                        Toast.makeText(ScreenOTPActivity.this, "Sai Otp", Toast.LENGTH_LONG).show();
                        setLoadingState(false);
                    }
                }
                else
                {
                    setLoadingState(false);
                    Toast.makeText(ScreenOTPActivity.this, "Lỗi khi xác thực Otp", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(ScreenOTPActivity.this, "Lỗi khi xác thực Otp", Toast.LENGTH_LONG).show();
                setLoadingState(false);
            }
        });

        setLoadingState(true);

        //delete this before calling real api
        /*Intent intent = new Intent(this, DeclarePersonalInfoActivity.class);
        intent.putExtra("phone", phone);
        startActivity(intent);
        finish();*/
    }

    private void setLoadingState(boolean isLoading)
    {
        int v = isLoading ? View.VISIBLE : View.INVISIBLE;
        otpText.setEnabled(!isLoading);
        confirmOtpBtn.setEnabled(!isLoading);
        loadingOverlay.setVisibility(v);
        loadingBar.setVisibility(v);
    }

    private void checkAccExisted()
    {
        Call<CreateAccDto> call = otpService.findAccByPhone(phone);

        call.enqueue(new Callback<CreateAccDto>() {
            @Override
            public void onResponse(Call<CreateAccDto> call, Response<CreateAccDto> response) {

                if(response.isSuccessful())
                {
                    CreateAccDto res = response.body();

                    if(res!=null && res.phone.contentEquals(phone))
                    {
                        Intent intent = new Intent(ScreenOTPActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else
                {
                    Intent intent = new Intent(ScreenOTPActivity.this, DeclarePersonalInfoActivity.class);
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<CreateAccDto> call, Throwable t) {
                t.printStackTrace();
                setLoadingState(false);
                Toast.makeText(ScreenOTPActivity.this, "Lỗi", Toast.LENGTH_LONG).show();
            }
        });
    }
}
