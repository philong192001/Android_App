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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appcovid.network.AccountService;
import com.example.appcovid.network.NetworkModule;
import com.example.appcovid.network.dto.CommuneDto;
import com.example.appcovid.network.dto.CreateAccDto;
import com.example.appcovid.network.dto.DistrictDto;
import com.example.appcovid.network.dto.MessDto;
import com.example.appcovid.network.dto.ProvinceDto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfomationUserActivity extends AppCompatActivity {

    CheckBox cbSuccess;
    Button btnUpdate;
    EditText fullname;
    EditText CMTND;
    EditText address;
    EditText phone;
    EditText email;
    EditText birthday;
    RadioButton gender;
    CreateAccDto acc = new CreateAccDto();
    Spinner spin_province;
    Spinner spin_district;
    Spinner spin_wards;
    //getText
    String getname ;
    private AccountService accountService = NetworkModule.accountService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation_user);

        cbSuccess = findViewById(R.id.cbSuccess);
        btnUpdate = findViewById(R.id.btn_update);
        fullname = findViewById(R.id.et_name);
        CMTND = findViewById(R.id.et_cmt);
        address = findViewById(R.id.et_address);
        phone = findViewById(R.id.et_phone);
        email = findViewById(R.id.et_email);
        gender = findViewById(R.id.radio_male);
        birthday = findViewById(R.id.et_dob);
        spin_province = (Spinner) findViewById(R.id.spinner_province);
        spin_district = (Spinner) findViewById(R.id.spinner_district);
        spin_wards = (Spinner) findViewById(R.id.spinner_wards);

        btnUpdate.setEnabled(false);

        if(cbSuccess.isChecked()){
            btnUpdate.setEnabled(true);
        }else{
            btnUpdate.setEnabled(false);
        }

        loadSpinners();
        acc = (CreateAccDto) getIntent().getSerializableExtra("info");

        fullname.setText(acc.name);
        CMTND.setText(acc.cmt);
        address.setText(acc.address);
        phone.setText(acc.phone);
        gender.setChecked(acc.gender);
        birthday.setText(convertDateToString(acc.birthDay));
        //Log.d("INFOOOOO" , acc.toString());
        btnUpdate.setOnClickListener(v -> UpdateAccount());
    }

    private String convertDateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
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
                            (InfomationUserActivity.this, android.R.layout.simple_spinner_dropdown_item, body);

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
                            (InfomationUserActivity.this, android.R.layout.simple_spinner_dropdown_item, body);

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
                            (InfomationUserActivity.this, android.R.layout.simple_spinner_dropdown_item, body);

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
    public void Check(View view) {
        if(cbSuccess.isChecked() == true){
            btnUpdate.setEnabled(true);
        }else{
            btnUpdate.setEnabled(false);
        }
    }

    public void UpdateAccount()
    {
        if (!validateFullName()| !validateAddress() | !validateCMT()  | !validatePhone() | !validateEmail() ) {
            return;
        }

        CreateAccDto updateacc = new CreateAccDto();
        updateacc.name =  fullname.getText().toString();
        updateacc.cmt = CMTND.getText().toString();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            updateacc.birthDay = df.parse(birthday.getText().toString());
        }
        catch (Exception e)
        {
            Log.d("ERROR",e.getMessage());
        }
        updateacc.gender = gender.isChecked();
        updateacc.idCommune = ((CommuneDto) spin_wards.getSelectedItem()).communeId;
        updateacc.address = address.getText().toString();
        updateacc.phone = phone.getText().toString();

        Log.d("INFO EMP" , updateacc.toString());

        Call<MessDto> call = accountService.updateAccount(updateacc);
        call.enqueue(new Callback<MessDto>() {
            @Override
            public void onResponse(Call<MessDto> call, Response<MessDto> response) {
                if(response.isSuccessful())
                {
                    MessDto r = response.body();
                    Toast.makeText(InfomationUserActivity.this, "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(InfomationUserActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {

                }
            }

            @Override
            public void onFailure(Call<MessDto> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(InfomationUserActivity.this, "Lỗi khi update account", Toast.LENGTH_LONG).show();
            }
        });

    }

    private boolean validateFullName(){
        String val = fullname.getText().toString().trim();
        if (val.isEmpty()) {
            fullname.setError("Field can not be empty");
            return false;
        } else {
            fullname.setError(null);
            return true;
        }
    }
    private boolean validateCMT(){
        String val = CMTND.getText().toString().trim();
        if (val.isEmpty()) {
            CMTND.setError("Field can not be empty");
            return false;
        } else {
            CMTND.setError(null);
            return true;
        }
    }
    private boolean validateAddress(){
        String val = address.getText().toString().trim();
        if (val.isEmpty()) {
            address.setError("Enter valid Address");
            return false;
        } else {
            address.setError(null);
            return true;
        }
    }
    private boolean validatePhone(){
        String val = phone.getText().toString().trim();
        if (val.isEmpty()) {
            phone.setError("Enter valid phone number");
            return false;
        }else if (val.length() > 10) {
            phone.setError("phone is too long!");
            return false;
        }else if (val.length() < 10) {
            phone.setError("phone is too short!");
            return false;
        } else {
            phone.setError(null);
            return true;
        }
    }
    private boolean validateEmail(){
        String val = email.getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            email.setError("Invalid Email!");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }
}