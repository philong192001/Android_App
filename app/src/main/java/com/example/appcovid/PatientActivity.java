package com.example.appcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.appcovid.network.AccountService;
import com.example.appcovid.network.DeclareService;
import com.example.appcovid.network.NetworkModule;
import com.example.appcovid.network.dto.CreateAccDto;
import com.example.appcovid.network.dto.MessDto;
import com.example.appcovid.network.dto.PostDecDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientActivity extends AppCompatActivity {

    private RadioButton rbtCo;
    private RadioButton rbtKhong;
    private RadioButton rbtCo1;
    private RadioButton rbtKhong1;
    private RadioButton rbtCo2;
    private RadioButton rbtKhong2;
    private RadioGroup rdgtiepxuc;
    private RadioGroup rdgdivetuvungdich;
    private RadioGroup rdgtiepxucvungdich;
    private EditText etThongtin;

    private CheckBox cbSot;
    private CheckBox cbHo;
    private CheckBox cbKhotho;
    private CheckBox cbViemphoi;
    private CheckBox cbDauhong;
    private CheckBox cbMetmoi;

    private CheckBox cbGanMt;
    private CheckBox cbMauMt;
    private CheckBox cbPhoiMt;
    private CheckBox cbThanMt;
    private CheckBox cbTimMach;
    private CheckBox cbHuyetApCap;
    private CheckBox cbHIV;
    private CheckBox cbGhepTang;
    private CheckBox cbTieuDuong;
    private CheckBox cbUngThu;
    private CheckBox cbCoThai;

    private CheckBox cbOK;

    private DeclareService declareService = NetworkModule.declareService;

    PostDecDto dec;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        dec = ((PostDecDto) getIntent().getSerializableExtra("info_user_1"));

        rbtCo = findViewById(R.id.radio_co);
//        rbtKhong = findViewById(R.id.radio_khong);
        rbtCo1 = findViewById(R.id.radio_co1);
        //rbtKhong1 = findViewById(R.id.radio_khong1);
        rbtCo2 = findViewById(R.id.radio_co2);
        //rbtKhong2 = findViewById(R.id.radio_khong2);

        etThongtin = findViewById(R.id.editTextThongtin);

        cbSot = (CheckBox) findViewById(R.id.checkbox_sot);
        cbHo = (CheckBox) findViewById(R.id.checkbox_ho);
        cbKhotho = (CheckBox) findViewById(R.id.checkbox_khotho);
        cbViemphoi = (CheckBox) findViewById(R.id.checkbox_viemphoi);
        cbDauhong = (CheckBox) findViewById(R.id.checkbox_dauhong);
        cbMetmoi = (CheckBox) findViewById(R.id.checkbox_metmoi);

        cbGanMt = (CheckBox) findViewById(R.id.checkbox_ganmantinh);
        cbMauMt = (CheckBox) findViewById(R.id.checkbox_maumantinh);
        cbPhoiMt = (CheckBox) findViewById(R.id.checkbox_phoimantinh);
        cbThanMt = (CheckBox) findViewById(R.id.checkbox_thanmantinh);
        cbTimMach = (CheckBox) findViewById(R.id.checkbox_timamch);
        cbHuyetApCap = (CheckBox) findViewById(R.id.checkbox_huyetapcao);
        cbHIV = (CheckBox) findViewById(R.id.checkbox_hiv);
        cbGhepTang = (CheckBox) findViewById(R.id.checkbox_gheptang);
        cbTieuDuong =(CheckBox)  findViewById(R.id.checkbox_tieuduong);
        cbUngThu = (CheckBox) findViewById(R.id.checkbox_ungthu);
        cbCoThai = (CheckBox) findViewById(R.id.checkbox_cothai);
        etThongtin = findViewById(R.id.editTextThongtin);

        cbOK = (CheckBox) findViewById(R.id.ok);
        btn = (Button) findViewById(R.id.btn_submit);
        btn.setEnabled(false);
        //
        rdgtiepxuc = (RadioGroup)findViewById(R.id.rdgtiepxuc);
        rdgdivetuvungdich = (RadioGroup) findViewById(R.id.divetuvungdich);
        rdgtiepxucvungdich = (RadioGroup) findViewById(R.id.tiepxucvoithangngudituvungdichve);

        radioButton = findViewById(R.id.radio_co);
        radioButton = findViewById(R.id.radio_co1);
        radioButton = findViewById(R.id.radio_co2);
        btn.setOnClickListener(v -> Submit());
    }

    public void Submit() {
        if(!ValidationExposureToCovid() | !ValidationComingFromCovidArea() | !ValidationContactWithACaseOfReturningFromTheCovidArea() | !validate14days()){
            return;
        }

        PostDecDto dto = new PostDecDto();
        dto.name = dec.name;
        dto.cmt = dec.cmt;
        dto.BHXH = dec.BHXH;
        dto.birthDay = dec.birthDay;
        dto.gender = dec.gender;
        dto.idCommune = dec.idCommune;
        dto.address = dec.address;
        dto.phone = dec.phone;
        dto.email = dec.email;

        dto.xposureToF0 = rbtCo.isChecked();
        dto.comeBackFromEpidemicArea = rbtCo1.isChecked();
        dto.contactWithPeopleReturningFromEpidemicAreas = rbtCo2.isChecked();

        dto.fever = cbSot.isChecked();
        dto.cough = cbHo.isChecked();
        dto.shortnessOfBreath = cbKhotho.isChecked();
        dto.pneumonia = cbViemphoi.isChecked();
        dto.soreThroat = cbDauhong.isChecked();
        dto.tired = cbMetmoi.isChecked();

        dto.chronicLiverDisease = cbGanMt.isChecked();
        dto.chronicBloodDisease = cbMauMt.isChecked();
        dto.chronicLungDisease = cbPhoiMt.isChecked();
        dto.chronicKideyDisease = cbThanMt.isChecked();
        dto.heartRelatedDiseaes = cbTimMach.isChecked();
        dto.highBloodPressure = cbHuyetApCap.isChecked();
        dto.hivOrImmunocompromised = cbHIV.isChecked();
        dto.organTransplantRecipient = cbGhepTang.isChecked();
        dto.diabetes = cbTieuDuong.isChecked();
        dto.cancer = cbUngThu.isChecked();
        dto.pregnant = cbCoThai.isChecked();

        dto.travelSchedule = etThongtin.getText().toString();
        //Log.d("ASSS", dto.toString());


        Call<MessDto> call = declareService.postDeclare(dto);
        call.enqueue(new Callback<MessDto>() {
            @Override
            public void onResponse(Call<MessDto> call, Response<MessDto> response) {
                //Log.d("ALo ALo","123");
                if(response.isSuccessful())
                {
                    MessDto r = response.body();
                    ///Log.d("ALo ALo",dto.toString());
                    Toast.makeText(PatientActivity.this, "Khai báo thành công", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(PatientActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
            @Override
            public void onFailure(Call<MessDto> call, Throwable t) {
                cbOK.setEnabled(false);
                t.printStackTrace();
                Toast.makeText(PatientActivity.this, "Lỗi khi khai bao y te", Toast.LENGTH_LONG).show();
            }
        });


    }

    private boolean ValidationExposureToCovid(){
        int isSelected = rdgtiepxuc.getCheckedRadioButtonId();

        if(isSelected == -1){
            Toast.makeText(PatientActivity.this,"Khai báo đi má",Toast.LENGTH_LONG).show();
            return false;
        }else{
            return  true;
        }
    }
    private boolean ValidationComingFromCovidArea(){
        int isSelected = rdgdivetuvungdich.getCheckedRadioButtonId();

        if(isSelected == -1){
            Toast.makeText(PatientActivity.this,"Khai báo đi má",Toast.LENGTH_LONG).show();
            return false;
        }else{
            return  true;
        }
    }
    private boolean ValidationContactWithACaseOfReturningFromTheCovidArea(){
        int isSelected = rdgtiepxucvungdich.getCheckedRadioButtonId();

        if(isSelected == -1){
            Toast.makeText(PatientActivity.this,"Khai báo đi má",Toast.LENGTH_LONG).show();
            return false;
        }else{
            return  true;
        }
    }
    private boolean validate14days() {
        String val = etThongtin.getText().toString().trim();
        if (val.isEmpty()) {
            etThongtin.setError("Field can not be empty");
            return false;
        } else {
            etThongtin.setError(null);
            return true;
        }
    }

    public void Check(View view) {
        if(cbOK.isChecked()){
            btn.setEnabled(true);
        }else{
            btn.setEnabled(false);
        }
    }
}