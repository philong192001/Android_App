package com.example.appcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

public class DeclarePersonalInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String phone = "";

    private EditText etFullname;
    private EditText etCmt;
    private EditText etBhxh;
    private EditText etDob;
    private EditText etAddress;
    private EditText etPhone;
    private RadioButton rbtMale;

    private Button btnReg;

    private AccountService accountService = NetworkModule.accountService;

    Spinner spin_province;
    Spinner spin_district;
    Spinner spin_wards;

    CreateAccDto acc;

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

        acc = ((CreateAccDto) getIntent().getSerializableExtra("accinfo"));

        etFullname.setText(acc.name);
        etCmt.setText(acc.cmt);
        String dob = new SimpleDateFormat("dd/MM/yyyy").format(acc.birthDay);
        etDob.setText(dob);
        etAddress.setText(acc.address);
        etPhone.setText(acc.phone);
        rbtMale.setChecked(acc.gender);

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
        Intent intent = new Intent(this, PatientActivity.class);
        startActivity(intent);
    }
}