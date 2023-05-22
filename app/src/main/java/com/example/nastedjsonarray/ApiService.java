package com.example.nastedjsonarray;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("v3/decba4ce-a37b-4354-a042-093d998038fa") // Replace with your actual API endpoint
    Call<YourResponseModel> getResponse();
}
