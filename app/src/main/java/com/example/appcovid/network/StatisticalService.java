package com.example.appcovid.network;

import com.example.appcovid.network.dto.DiseaseInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StatisticalService {
    @GET("/football-shop-web-app-0.0.1-SNAPSHOT/v1/statistical/staticalTotalPeopleByStatus")
    Call<Map<String,Long>> getStatistical();

    @GET("/football-shop-web-app-0.0.1-SNAPSHOT/v1/statistical/dataFileJsonNcov")
    Call<DiseaseInfo> getDataNcov();


}
