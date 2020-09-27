package com.gdr.mallutorrentz;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainInterface {

    @GET("movies_updates")
    Call<String> STRING_CALL(
            @Query("page") int page,
            @Query("limit") int limit
    );

    @GET("fetch")
    Call<String> FETCH_CALL(
            @Query("page") int page,
            @Query("limit") int limit
    );

    @GET("latest")
    Call<String> LATEST_CALL(
            @Query("page") int page,
            @Query("limit") int limit
    );

    @GET("latest")
    Call<String> MALAYALAM_CALL(
            @Query("page") int page,
            @Query("limit") int limit
    );


}
