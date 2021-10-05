package com.example.appcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.appcovid.network.dto.CreateAccDto;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRCodeActivity extends AppCompatActivity {

    EditText etInput;
    Button btGene;
    ImageView ivQR;

    CreateAccDto dto = new CreateAccDto();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        etInput = findViewById(R.id.et_qr);
        btGene = findViewById(R.id.generate);
        ivQR = findViewById(R.id.output);

        btGene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sText = etInput.getText().toString().trim();
                dto.cmt = "124";
                dto.name = sText;
                dto.phone="0355882001";

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
                    InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                    manager.hideSoftInputFromWindow(etInput.getApplicationWindowToken(),0);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}