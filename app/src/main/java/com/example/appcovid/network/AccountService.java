package com.example.appcovid.network;

import com.example.appcovid.network.dto.CommuneDto;
import com.example.appcovid.network.dto.CreateAccDto;
import com.example.appcovid.network.dto.DistrictDto;
import com.example.appcovid.network.dto.MessDto;
import com.example.appcovid.network.dto.ProvinceDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AccountService {

    @POST("/football-shop-web-app-0.0.1-SNAPSHOT/v1/declare/createAccount")
    Call<MessDto> createAccount(@Body CreateAccDto body);

    @POST("/football-shop-web-app-0.0.1-SNAPSHOT/v1/declare/updateAccount")
    Call<MessDto> updateAccount(@Body CreateAccDto body);

    @GET("/football-shop-web-app-0.0.1-SNAPSHOT/v1/address/allProvince")
    Call<List<ProvinceDto>> getAllProvince();

    @GET("/football-shop-web-app-0.0.1-SNAPSHOT/v1/address/allDistrict")
    Call<List<DistrictDto>> getAllDistrict();

    @GET("/football-shop-web-app-0.0.1-SNAPSHOTv1/address/allCommune")
    Call<List<CommuneDto>>  getAllCommnue();

    @GET("/football-shop-web-app-0.0.1-SNAPSHOT/v1/address/getAllCommuneByDistrictId/{idDistrict}")
    Call<List<CommuneDto>> getCommnueById(@Path("idDistrict") long idDistrict);

    @GET("/football-shop-web-app-0.0.1-SNAPSHOT/v1/address/getAllDistrictByProvinceId/{idProvince}")
    Call<List<DistrictDto>> getDistrictById(@Path("idProvince") long idProvince);
}
