package com.example.mymovie.api;

import com.example.mymovie.model.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiListener {
    @GET("top_rated?")
    Call<DataResponse> getMovieTopRateList(@Query("api_key") String apiKey,
                                           @Query("language") String language,
                                           @Query("page") int page,
                                           @Query("region") String region);
}
