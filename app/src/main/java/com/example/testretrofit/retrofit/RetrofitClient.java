package com.example.testretrofit.retrofit;

import com.example.testretrofit.movies_api.MoviesAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static MoviesAPI instance;



    //Esto es un builder
    public static MoviesAPI getInstance() {
        if (instance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            instance = retrofit.create(MoviesAPI.class);
        }

        return instance;
    }
}
