package com.example.appcovid.network.dto;

import androidx.annotation.NonNull;

public class DistrictDto {
    public long districtId;
    public String districtName;
    public String type;
    public ProvinceDto province;

    @NonNull
    @Override
    public String toString() {
        return districtName;
    }
}
