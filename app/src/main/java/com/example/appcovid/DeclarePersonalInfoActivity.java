package com.example.appcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appcovid.network.AccountService;
import com.example.appcovid.network.NetworkModule;
import com.example.appcovid.network.dto.CreateAccDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeclarePersonalInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] province = { "Chọn Tỉnh/Thành","Quảng Ninh", "Bắc Giang", "Hà Nội", "Thanh Hóa", "Hải Dương", "Hưng Yên"};
    String[] district = {"Chọn Quận/Huyện","1","2","3"};
    String[] wards = {"Chọn Xã/Phường","Vàng Danh ", "Bắc Sơn","Thanh Sơn"};

    String phone = "";

    private EditText etFullname;
    private EditText etCmt;
    private EditText etBhxh;
    private EditText etDob;
    private EditText etAddress;
    private EditText etPhone;
    private EditText etEmail;
    private RadioButton rbtMale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declare_personal_info);


        etFullname = findViewById(R.id.et_name);
        etCmt = findViewById(R.id.et_cmt);
        etBhxh = findViewById(R.id.et_bhxh);
        etDob = findViewById(R.id.et_dob);
        etAddress = findViewById(R.id.et_address);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);
        rbtMale = findViewById(R.id.radio_male);

        phone = getIntent().getStringExtra("phone");

        etPhone.setText(phone);

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        boolean checked = ((RadioButton) view).isChecked();
//
//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.radio_pirates:
//                if (checked)
//                    // Pirates are the best
//                    break;
//            case R.id.radio_ninjas:
//                if (checked)
//                    // Ninjas rule
//                    break;
//        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void submitInfo(){
        CreateAccDto dto = new CreateAccDto();
        dto.name = etFullname.getText().toString();
        dto.birthDay = etDob.getText().toString();
        dto.cmt = etCmt.getText().toString();
        dto.gender = rbtMale.isChecked();
        dto.phone = etPhone.getText().toString();
        dto.idCommune = 0;
        dto.address = etAddress.getText().toString();


        Call<String> call = NetworkModule.accountService.createAccount(dto);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(DeclarePersonalInfoActivity.this, "Lỗi khi tạo account", Toast.LENGTH_LONG).show();
            }
        });
    }
}