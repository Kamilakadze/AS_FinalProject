package com.example.project.api;

import com.example.project.model.LoginResponse;
import com.example.project.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/api/register")
    Call<LoginResponse> register(@Body User user);

    Call<LoginResponse> login(User user);
}
