package com.example.appcovid.network;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OtpService {
    @POST("/v1/sms/mobile")
    Call<Boolean> createOtp(@Body Map<String, String> body);
}
