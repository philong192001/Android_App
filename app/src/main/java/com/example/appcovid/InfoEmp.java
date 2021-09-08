package com.example.appcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class InfoEmp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] province = { "Chọn Tỉnh/Thành","Quảng Ninh", "Bắc Giang", "Hà Nội", "Thanh Hóa", "Hải Dương", "Hưng Yên"};
    String[] district = {"Chọn Quận/Huyện","1","2","3"};
    String[] wards = {"Chọn Xã/Phường","Vàng Danh ", "Bắc Sơn","Thanh Sơn"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_emp);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinner_province);
        Spinner spin_district = (Spinner) findViewById(R.id.spinner_district);
        Spinner spin_wards = (Spinner) findViewById(R.id.spinner_wards);
        spin.setOnItemSelectedListener(this);
        spin_district.setOnItemSelectedListener(this);
        spin_wards.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,province);
        ArrayAdapter list_district = new ArrayAdapter(this,android.R.layout.simple_spinner_item,district);
        ArrayAdapter list_wards = new ArrayAdapter(this,android.R.layout.simple_spinner_item,wards);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        list_district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        list_wards.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        spin_district.setAdapter(list_district);
        spin_wards.setAdapter(list_wards);
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_pirates:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radio_ninjas:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),province[position] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}