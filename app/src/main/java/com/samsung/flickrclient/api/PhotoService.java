package com.samsung.flickrclient.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by sidia on 09/02/18.
 */

public interface PhotoService {

    @GET("/")
    @Headers({"content-type:application/json", "accept:application/json"})
    Call<String> getRecent(@Query("method") String method,
                           @Query("api_key") String apiKey,
                           @Query("format") String format,
                           @Query("nojsoncallback") String callback,
                           @Query("extras") String extras);
}
