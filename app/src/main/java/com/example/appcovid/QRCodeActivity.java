package com.example.appcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appcovid.network.NetworkModule;
import com.example.appcovid.network.OtpService;
import com.example.appcovid.network.dto.CreateAccDto;
import com.example.appcovid.network.dto.QRCode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QRCodeActivity extends AppCompatActivity {

    ImageView ivQR;
    TextView txfullname;
    private QRCode qrCode = new QRCode();
    TextView txtime;
    private OtpService otpService = NetworkModule.otpService;
    private Date currentDateTime = Calendar.getInstance().getTime();
    private CreateAccDto acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        txtime = (TextView) findViewById(R.id.time);
        txfullname = (TextView) findViewById(R.id.fullname);
        acc = ((CreateAccDto) getIntent().getSerializableExtra("accinfo"));
        String phone = acc.phone;

        Log.d("acc",acc.getPhone());

        ivQR = findViewById(R.id.output);

        callAPIQRCode(phone);
    }

    private void callAPIQRCode(String phone) {
        Call<QRCode> call = otpService.QRCodeFindByPhone(phone);

        call.enqueue(new Callback<QRCode>() {
            @Override
            public void onResponse(Call<QRCode> call, Response<QRCode> response) {

                if (response.isSuccessful()) {

                    qrCode = response.body();
                    Log.d("RES", qrCode.toString());

                    txfullname.setText(qrCode.name);
                    txtime.setText(convertDateToString(currentDateTime));
                    Log.d("DTO", qrCode.toString());

                    MultiFormatWriter writer = new MultiFormatWriter();
                    //
                    try {
                        BitMatrix matrix = writer.encode(qrCode.toString(), BarcodeFormat.QR_CODE, 350, 350);
                        //
                        BarcodeEncoder encoder = new BarcodeEncoder();
                        //
                        Bitmap bitmap = encoder.createBitmap(matrix);
                        //set bitmap on image view
                        ivQR.setImageBitmap(bitmap);
                        //init input manager
                        //InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                        //manager.hideSoftInputFromWindow(txfullname.getApplicationWindowToken(),0);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<QRCode> call, Throwable t) {

            }
        });
    }


    private String convertDateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss, dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public void Return(View view) {
        Intent intent = new Intent(QRCodeActivity.this, HomeActivity.class);
        intent.putExtra("accinfo", acc);
        startActivity(intent);
        finish();
    }
}