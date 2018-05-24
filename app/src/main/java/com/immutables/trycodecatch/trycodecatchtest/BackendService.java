package com.immutables.trycodecatch.trycodecatchtest;

import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.LoginModel;
import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.LoginResponse;
import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.RegisterResponse;
import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BackendService
{

    @POST("/api/v1/auth/token")
    Call<LoginResponse> loginUser(@Body LoginModel user);


    @POST("/api/v1/users")
    Call<RegisterResponse> registerUser(@Body User user);
}
