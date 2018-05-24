package com.immutables.trycodecatch.trycodecatchtest;

import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.DonationResponse;
import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.LoginModel;
import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.LoginResponse;
import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.RegisterResponse;
import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.UserRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BackendService
{

    @POST("/api/v1/auth/token")
    Call<LoginResponse> loginUser(@Body LoginModel user);


    @POST("/api/v1/users")
    Call<RegisterResponse> registerUser(@Body UserRegister user);

    @GET("/api/v1/user-donation/user/{id}")
    Call<DonationResponse> getUserDonations(@Header("Authorization") String authorization, @Path("id") String id);
}
