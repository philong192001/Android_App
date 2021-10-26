package com.example.appcovid.network;

import com.example.appcovid.network.dto.CreateAccDto;
import com.example.appcovid.network.dto.QRCode;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OtpService {
    @POST("/football-shop-web-app-0.0.1-SNAPSHOT/v1/sms/mobile")
    Call<Boolean> createOtp(@Body Map<String, String> body);

    @POST("/football-shop-web-app-0.0.1-SNAPSHOT/v1/sms/verifyOtp")
    Call<Boolean> confirmOtp(@Body Map<String, String> body);

    @POST("/football-shop-web-app-0.0.1-SNAPSHOT/v1/declare/findAccountByPhone")
    Call<CreateAccDto> findAccByPhone(@Query("phone") String phone);

    @POST("/football-shop-web-app-0.0.1-SNAPSHOT/v1/declare/detailDeclareRecent")
    Call<QRCode> QRCodeFindByPhone(@Query("phone")String phone);
}
