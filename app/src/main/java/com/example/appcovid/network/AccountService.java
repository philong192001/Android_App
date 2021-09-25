package com.example.appcovid.network;

import com.example.appcovid.network.dto.CreateAccDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountService {
    @POST("/v1/declare/createAccount")
    Call<String> createAccount(@Body CreateAccDto body);
}
