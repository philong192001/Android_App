package com.example.appcovid.network;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OtpService {
    @POST("/football-shop-web-app-0.0.1-SNAPSHOT/v1/sms/mobile")
    Call<Boolean> createOtp(@Body Map<String, String> body);

    @POST("/football-shop-web-app-0.0.1-SNAPSHOT/v1/sms/verifyOtp")
    Call<Boolean> confirmOtp(@Body Map<String, String> body);
}
