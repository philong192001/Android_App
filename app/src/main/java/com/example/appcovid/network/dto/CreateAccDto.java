package com.example.appcovid.network.dto;

import java.io.Serializable;
import java.util.Date;

public class CreateAccDto implements Serializable {
    public String name;
    public Date birthDay;
    public String cmt;
    public boolean gender;
    public String phone;
    public long idCommune;
    public String address;
}


