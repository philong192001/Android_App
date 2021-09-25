package com.example.appcovid.network.dto;

import androidx.annotation.NonNull;

public class ProvinceDto {
    public long provinceId;
    public String provinceName;
    public String type;
    public String slug;

    @NonNull
    @Override
    public String toString() {
        return provinceName;
    }
}
