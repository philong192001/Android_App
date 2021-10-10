package com.example.appcovid.network.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class CreateAccDto implements Serializable {

    @Expose
    @SerializedName( "Họ và Tên")
    public String name;
    public Date birthDay;
    public String cmt;
    public boolean gender;
    public String phone;
    public long idCommune;
    public String address;


    private String checkGender(){
        if(gender == true){
            return "Nam";
        }else{
            return  "Nữ";
        }

    }
    @Override
    public String toString() {
        return "Thong tin khai bao{" +
                "Ho va Ten ='" + name + '\'' +
                ", Ngày Sinh =" + birthDay +
                ", Sô CMTND='" + cmt + '\'' +
                ", Giơi Tính=" + checkGender() +
                ", Sô Diên Thoai ='" + phone + '\'' +
                ", Dia Chi =" + idCommune +
                ", Sô duong , nha='" + address + '\'' +
                '}';
    }


}


