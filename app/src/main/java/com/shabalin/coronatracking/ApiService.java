package com.shabalin.coronatracking;

import com.shabalin.coronatracking.data.CountriesList;
import com.shabalin.coronatracking.data.Global;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/summary")
    Call<CountriesList> getAllCountries();

    @GET("/summary")
    Call<Global> getGlobal();
}
