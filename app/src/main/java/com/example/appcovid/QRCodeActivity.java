package com.example.appcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appcovid.network.dto.CreateAccDto;
import com.example.appcovid.network.dto.QRcode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class QRCodeActivity extends AppCompatActivity {

    ImageView ivQR;
    TextView txfullname;
    CreateAccDto dto ;
    TextView txtime;
    private Date currentDateTime = Calendar.getInstance().getTime();

    QRcode qrcode = new QRcode();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        txtime = (TextView) findViewById(R.id.time);
        txfullname = (TextView) findViewById(R.id.fullname) ;
        dto = ((CreateAccDto) getIntent().getSerializableExtra("accinfo"));
        ivQR = findViewById(R.id.output);

        txfullname.setText(dto.name);
        txtime.setText(convertDateToString(currentDateTime));
        Log.d("DTO" ,dto.toString());

        MultiFormatWriter writer = new MultiFormatWriter();
        //
        try {
            BitMatrix matrix = writer.encode(dto.toString(), BarcodeFormat.QR_CODE,350,350);
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
    private String convertDateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss, dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public void Return(View view) {
        Intent intent = new Intent(QRCodeActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}