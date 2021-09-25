package com.example.appcovid.network;

import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class NetworkModule {
    private NetworkModule() {}

    private static String BASE_URL="http://10.0.2.2:5000";

    public static Moshi moshi = new Moshi.Builder().build();

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build();

    public static OtpService otpService = retrofit.create(OtpService.class);
    public static AccountService accountService = retrofit.create(AccountService.class);
}
