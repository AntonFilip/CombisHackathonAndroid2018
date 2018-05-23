package com.immutables.trycodecatch.trycodecatchtest;

import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.LoginModel;
import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BackendService
{

    @POST("/api/v1/auth/token")
    Call<LoginResponse> loginUser(@Body LoginModel user);
}
