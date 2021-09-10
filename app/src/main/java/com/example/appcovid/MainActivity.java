package com.example.appcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Đăng nhập ");
        setVariables();
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if( v.getId() == R.id.bt_VerificationPhone ) {
            Intent i = new Intent(MainActivity.this, InfoEmp.class);
            startActivity(i);
        }

    }
    private void setVariables() {
        btn = (Button)findViewById(R.id.bt_VerificationPhone);
    }
}