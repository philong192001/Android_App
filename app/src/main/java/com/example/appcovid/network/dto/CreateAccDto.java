package com.example.appcovid.network.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class CreateAccDto implements Serializable {

    @Expose
    @SerializedName( "Họ và Tên")
    public int Id;
    public String name;
    public Date birthDay;
    public String cmt;
    public boolean gender;
    public String phone;
    public long idCommune;
    public String address;

    public CreateAccDto(String name, Date birthDay, String cmt, boolean gender, String phone, long idCommune, String address) {
        this.name = name;
        this.birthDay = birthDay;
        this.cmt = cmt;
        this.gender = gender;
        this.phone = phone;
        this.idCommune = idCommune;
        this.address = address;
    }

    public CreateAccDto() {
    }

    public CreateAccDto(int id, String name, String cmt, String phone, long idCommune, String address) {
        Id = id;
        this.name = name;
        this.cmt = cmt;
        this.phone = phone;
        this.idCommune = idCommune;
        this.address = address;
    }

    public CreateAccDto(String name, String cmt, String phone, String address) {
        this.name = name;
        this.cmt = cmt;
        this.phone = phone;
        this.address = address;
    }


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
                ", Sô duong, nha='" + address + '\''
                ;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public long getIdCommune() {
        return idCommune;
    }

    public void setIdCommune(long idCommune) {
        this.idCommune = idCommune;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}


