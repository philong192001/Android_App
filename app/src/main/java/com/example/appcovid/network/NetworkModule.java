package com.example.appcovid.network;

import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter;

import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class NetworkModule {
    private NetworkModule() {}

    private static String BASE_URL="http://192.168.12.206:8082";

    public static Moshi moshi = new Moshi.Builder()
            .add(Date.class, new Rfc3339DateJsonAdapter())
            .build();

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build();

    public static OtpService otpService = retrofit.create(OtpService.class);
    public static AccountService accountService = retrofit.create(AccountService.class);
    public static StatisticalService statisticalService = retrofit.create(StatisticalService.class);
    public static DeclareService declareService = retrofit.create(DeclareService.class);
}
