package com.example.appcovid.network;

import com.example.appcovid.network.dto.MessDto;
import com.example.appcovid.network.dto.PostDecDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DeclareService {
    @POST("/football-shop-web-app-0.0.1-SNAPSHOT/v1/declare")
    Call<MessDto> postDeclare(@Body PostDecDto dec);
}
