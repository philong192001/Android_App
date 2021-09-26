package com.example.appcovid.network.dto;

import androidx.annotation.NonNull;

public class CommuneDto {
    public long communeId;
    public String communeName;
    public String type;
    DistrictDto district;

    @NonNull
    @Override
    public String toString() {
        return communeName;
    }
}
