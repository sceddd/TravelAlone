package com.example.myapplication.jsonplaceholder;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationCities {
    @GET("w/api.php?action=parse%20_cities_by_population&section=1&prop=text")
    Call<String> returnApi(@Query("page")String title);
}
