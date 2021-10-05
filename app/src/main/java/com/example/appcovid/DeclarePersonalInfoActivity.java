package com.example.appcovid;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appcovid.network.AccountService;
import com.example.appcovid.network.NetworkModule;
import com.example.appcovid.network.dto.CommuneDto;
import com.example.appcovid.network.dto.CreateAccDto;
import com.example.appcovid.network.dto.DistrictDto;
import com.example.appcovid.network.dto.MessDto;
import com.example.appcovid.network.dto.PostDecDto;
import com.example.appcovid.network.dto.ProvinceDto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeclarePersonalInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    CreateAccDto dto = new CreateAccDto();

    private EditText etFullname;
    private EditText etCmt;
    private EditText etBhxh;
    private EditText etDob;
    private EditText etAddress;
    private EditText etPhone;
    private RadioButton rbtMale;
    private EditText etEmail;
    private Button btnReg;

    private RadioGroup rdg;
    private AccountService accountService = NetworkModule.accountService;

    Spinner spin_province;
    Spinner spin_district;
    Spinner spin_wards;

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
        rbtMale = findViewById(R.id.radio_male);
        btnReg = findViewById(R.id.btn_register);
        etEmail = findViewById(R.id.et_email);
        rdg = (RadioGroup) findViewById(R.id.rdgGender);

        dto = (CreateAccDto) getIntent().getSerializableExtra("accinfo");
        etPhone.setText(dto.phone);
        etFullname.setText(dto.name);
        etCmt.setText(dto.cmt);
        etAddress.setText(dto.address);
        rbtMale.setChecked(dto.gender);


        spin_province = (Spinner) findViewById(R.id.spinner_province);
        spin_district = (Spinner) findViewById(R.id.spinner_district);
        spin_wards = (Spinner) findViewById(R.id.spinner_wards);

        loadSpinners();

        btnReg.setOnClickListener(v -> submitInfo());

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private void loadSpinners()
    {
        Call<List<ProvinceDto>> callp = accountService.getAllProvince();
        callp.enqueue(new Callback<List<ProvinceDto>>() {
            @Override
            public void onResponse(Call<List<ProvinceDto>> call, Response<List<ProvinceDto>> response) {
                if(response.isSuccessful())
                {
                    List<ProvinceDto> body = response.body();
                    ArrayAdapter aa = new ArrayAdapter<ProvinceDto>
                            (DeclarePersonalInfoActivity.this, android.R.layout.simple_spinner_dropdown_item, body);

                    spin_province.setAdapter(aa);

                    spin_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            long id = ((ProvinceDto) aa.getItem(i)).provinceId;
                            loadDistrict(id);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                else
                {

                }
            }

            @Override
            public void onFailure(Call<List<ProvinceDto>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadDistrict(long id)
    {
        Call<List<DistrictDto>> calld = accountService.getDistrictById(id);
        calld.enqueue(new Callback<List<DistrictDto>>() {
            @Override
            public void onResponse(Call<List<DistrictDto>> call, Response<List<DistrictDto>> response) {
                if(response.isSuccessful())
                {
                    List<DistrictDto> body = response.body();
                    ArrayAdapter aa = new ArrayAdapter<DistrictDto>
                            (DeclarePersonalInfoActivity.this, android.R.layout.simple_spinner_dropdown_item, body);

                    spin_district.setAdapter(aa);

                    spin_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            long id = ((DistrictDto) aa.getItem(i)).districtId;
                            loadWard(id);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<List<DistrictDto>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadWard(long id)
    {
        Call<List<CommuneDto>> callc = accountService.getCommnueById(id);
        callc.enqueue(new Callback<List<CommuneDto>>() {
            @Override
            public void onResponse(Call<List<CommuneDto>> call, Response<List<CommuneDto>> response) {
                if(response.isSuccessful())
                {
                    List<CommuneDto> body = response.body();
                    ArrayAdapter aa = new ArrayAdapter<CommuneDto>
                            (DeclarePersonalInfoActivity.this, android.R.layout.simple_spinner_dropdown_item, body);

                    spin_wards.setAdapter(aa);
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<List<CommuneDto>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void submitInfo(){
        btnReg.setEnabled(true);
        if (!validateFullName()| !validateAddress() | !validateCMT() | !validateBHXH() | !validateGender() | !validatePhone() | !validateEmail() ) {
            return;
        }

        PostDecDto dto = new PostDecDto();
        dto.name = etFullname.getText().toString();
        dto.birthDay = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dto.birthDay = df.parse(etDob.getText().toString());
        }
        catch (Exception e)
        {
            Log.d("ERROR",e.getMessage());
        }
        dto.cmt = etCmt.getText().toString();
        dto.BHXH = etBhxh.getText().toString();
        dto.gender = rbtMale.isChecked();
        dto.phone = etPhone.getText().toString();
        dto.idCommune = ((CommuneDto) spin_wards.getSelectedItem()).communeId;
        dto.address = etAddress.getText().toString();
        dto.email = etEmail.getText().toString();

        Log.d("INFO EMP" , dto.toString());



        Intent intent = new Intent(DeclarePersonalInfoActivity.this, PatientActivity.class);
        intent.putExtra("info_user_1", dto);
        startActivity(intent);
        finish();

//        Call<MessDto> call = accountService.createAccount(dto);
//        call.enqueue(new Callback<MessDto>() {
//            @Override
//            public void onResponse(Call<MessDto> call, Response<MessDto> response) {
//                btnReg.setEnabled(true);
//                if(response.isSuccessful())
//                {
//                    MessDto r = response.body();
//
//                }
//                else
//                {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MessDto> call, Throwable t) {
//                btnReg.setEnabled(true);
//                t.printStackTrace();
//                Toast.makeText(InfoEmp.this, "Lỗi khi tạo account", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    private boolean validateFullName(){
        String val = etFullname.getText().toString().trim();
        if (val.isEmpty()) {
            etFullname.setError("Field can not be empty");
            return false;
        } else {
            etFullname.setError(null);
            return true;
        }
    }
    private boolean validateCMT(){
        String val = etCmt.getText().toString().trim();
        if (val.isEmpty()) {
            etCmt.setError("Field can not be empty");
            return false;
        } else {
            etCmt.setError(null);
            return true;
        }
    }
    private boolean validateBHXH(){
        String val = etBhxh.getText().toString().trim();
        if (val.isEmpty()) {
            etBhxh.setError("Enter valid BHXH");
            return false;
        }else if (val.length() > 10) {
            etBhxh.setError("BHXH is too long!");
            return false;
        }else if (val.length() < 10) {
            etBhxh.setError("BHXH is too short!");
            return false;
        }  else {
            etBhxh.setError(null);
            return true;
        }
    }
//    private boolean Birthday(){
//        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
//        int userAge = etDob.get;
//        int isAgeValid = currentYear - userAge;
//
//        if (isAgeValid < 6) {
//            Toast.makeText(this, "You are not eligible to apply", Toast.LENGTH_SHORT).show();
//            return false;
//        } else
//            return true;
//    }
    private boolean validateGender(){
        if (rdg.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validateAddress(){
        String val = etAddress.getText().toString().trim();
        if (val.isEmpty()) {
            etAddress.setError("Enter valid Address");
            return false;
        } else {
            etAddress.setError(null);
            return true;
        }
    }
    private boolean validatePhone(){
        String val = etPhone.getText().toString().trim();
        if (val.isEmpty()) {
            etPhone.setError("Enter valid phone number");
            return false;
        }else if (val.length() > 10) {
            etPhone.setError("phone is too long!");
            return false;
        }else if (val.length() < 10) {
            etPhone.setError("phone is too short!");
            return false;
        } else {
            etPhone.setError(null);
            return true;
        }
    }
    private boolean validateEmail(){
        String val = etEmail.getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if (val.isEmpty()) {
            etEmail.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            etEmail.setError("Invalid Email!");
            return false;
        } else {
            etEmail.setError(null);
            return true;
        }
    }
}