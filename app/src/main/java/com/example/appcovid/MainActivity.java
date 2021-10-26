package com.example.appcovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcovid.network.dto.CreateAccDto;
import com.example.appcovid.sqllite.DatabaseHelper;
import com.example.appcovid.sqllite.MyDatabaseHelper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText edPhone;

    String[] permissions = new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private CreateAccDto accDto = new CreateAccDto();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        String a = readData();
//        Log.d("return alo",a);
//
//        if(!a.isEmpty()){
//            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//            intent.putExtra("phoneLoginSuccess", a);
//            startActivity(intent);
//            finish();
//            //loi la do chua tao class obj
//            Log.d("return abc",a);
//        }

        //SQLITE
        DatabaseHelper db = new DatabaseHelper(this);
        Log.d("DB",db.toString());
        try {
            accDto = db.getUser(70);
            Log.d("FPT",accDto.toString());
            if(accDto.getId() == 70){
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("accinfo", accDto);
                startActivity(intent);
                finish();
            }
            Log.d("coo data hay khong",accDto.toString());
        }catch (Exception e){
            Log.d("Exception","Data sqlite null");
        }

        Button btVerificationPhone = findViewById(R.id.bt_VerificationPhone);
        edPhone = findViewById(R.id.ed_phone);


        btVerificationPhone.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ScreenOTPActivity.class);

            String phone = edPhone.getText().toString();
            if(phone.isEmpty()){
                Toast.makeText(getApplicationContext(), "Nhập số điện thoại", Toast.LENGTH_LONG).show();
                return;
            }
            if (!validatePhone()) {
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

    private boolean validatePhone() {
        String val = edPhone.getText().toString().trim();
        String checkspaces = "Aw{1,20}z";

        if (val.isEmpty()) {
            edPhone.setError("Field can not be empty");
            return false;
        } else if (val.length() > 10) {
            edPhone.setError("Username is too long!");
            return false;
        }else if (val.length() < 10) {
            edPhone.setError("Username is too short!");
            return false;
        } else {
            edPhone.setError(null);
            return true;
        }
    }

    private String readData() {
        String dataFile = "";
        try {
            FileInputStream in = this.openFileInput("LoginSession.txt");

            BufferedReader br= new BufferedReader(new InputStreamReader(in));

            StringBuffer buffer = new StringBuffer();
            String line = null;
            while((line= br.readLine())!= null)  {
                buffer.append(line).append("\n");
            }
            Log.d("read-data:",buffer.toString());
            dataFile = buffer.toString();

            //return dataFile;

        } catch (Exception e) {
            Log.d("ERROR",e.getMessage());
            Toast.makeText(this,"Error:"+ e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        Log.d("Huan log:",dataFile);
        return dataFile;



    }

}