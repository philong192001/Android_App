package com.example.appcovid;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.example.appcovid.network.dto.ProvinceDto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String phone = "";

    private EditText etFullname;
    private EditText etCmt;
    private EditText etDob;
    private EditText etAddress;
    private EditText etPhone;
    private RadioButton rbtMale;
    private CheckBox cbConfirm;
    private Button btnReg;
    private RadioGroup rdgGender;
    private AccountService accountService = NetworkModule.accountService;

    Spinner spin_province;
    Spinner spin_district;
    Spinner spin_wards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullname = findViewById(R.id.et_name);
        etCmt = findViewById(R.id.et_cmt);
        etDob = findViewById(R.id.et_dob);
        etAddress = findViewById(R.id.et_address);
        etPhone = findViewById(R.id.et_phone);
        rbtMale = findViewById(R.id.radio_male);
        btnReg = findViewById(R.id.submitInfoPerson);
        cbConfirm = findViewById(R.id.confirm);
        phone = getIntent().getStringExtra("phone");

        etPhone.setText(phone);
        etPhone.setEnabled(false);
        rdgGender = (RadioGroup) findViewById(R.id.rdgGender);

        spin_province = (Spinner) findViewById(R.id.spinner_province);
        spin_district = (Spinner) findViewById(R.id.spinner_district);
        spin_wards = (Spinner) findViewById(R.id.spinner_wards);

        loadSpinners();

        btnReg.setEnabled(false);
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
                            (RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, body);

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
                            (RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, body);

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
                            (RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, body);

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

        if (!validateFullName()| !validateAddress() | !validateCMT()  | !validatePhone() | !ValidationGender()  ) {
            return;
        }
        CreateAccDto dto = new CreateAccDto();
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
        dto.gender = rbtMale.isChecked();
        dto.phone = etPhone.getText().toString();
        dto.idCommune = ((CommuneDto) spin_wards.getSelectedItem()).communeId;
        dto.address = etAddress.getText().toString();

        Log.d("INFO EMP" , dto.toString());

        Call<MessDto> call = accountService.createAccount(dto);
        call.enqueue(new Callback<MessDto>() {
            @Override
            public void onResponse(Call<MessDto> call, Response<MessDto> response) {
                btnReg.setEnabled(true);
                if(response.isSuccessful())
                {
                    MessDto r = response.body();

                    Toast.makeText(RegisterActivity.this, "Đăng kí tài khoản thành công", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    intent.putExtra("accinfo", dto);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<MessDto> call, Throwable t) {
                btnReg.setEnabled(true);
                t.printStackTrace();
                Toast.makeText(RegisterActivity.this, "Lỗi khi đăng ký tài khoản", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean validateFullName(){
        String val = etFullname.getText().toString().trim();
        if (val.isEmpty()) {
            etFullname.setError("Không được để trống");
            return false;
        } else {
            etFullname.setError(null);
            return true;
        }
    }
    private boolean validateCMT(){
        String val = etCmt.getText().toString().trim();
        if (val.isEmpty()) {
            etCmt.setError("Không được để trống");
            return false;
        } else {
            etCmt.setError(null);
            return true;
        }
    }
    private boolean validateAddress(){
        String val = etAddress.getText().toString().trim();
        if (val.isEmpty()) {
            etAddress.setError("Nhập địa chỉ hiện tại");
            return false;
        } else {
            etAddress.setError(null);
            return true;
        }
    }
    private boolean validatePhone(){
        String val = etPhone.getText().toString().trim();
        if (val.isEmpty()) {
            etPhone.setError("Nhập số điện thoại cá nhân");
            return false;
        }else if (val.length() > 10) {
            etPhone.setError("Số dài quá !");
            return false;
        }else if (val.length() < 10) {
            etPhone.setError("Số ngắn quá !");
            return false;
        } else {
            etPhone.setError(null);
            return true;
        }
    }

    private boolean ValidationGender(){
        int isSelected = rdgGender.getCheckedRadioButtonId();

        if(isSelected == -1){
            Toast.makeText(RegisterActivity.this,"Giới tính đâu , Ơ kìa",Toast.LENGTH_LONG).show();
            return false;
        }else{
            return  true;
        }
    }

    public void Check(View view) {
        if(cbConfirm.isChecked()){
            btnReg.setEnabled(true);
        }else{
            btnReg.setEnabled(false);
        }
    }
}