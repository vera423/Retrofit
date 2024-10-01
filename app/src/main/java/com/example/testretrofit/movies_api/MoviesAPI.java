package com.example.testretrofit.movies_api;

import com.example.testretrofit.json_mapper.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesAPI {
    //Routers!!! express.js
    //@GET("movie/popular?api_key=api_key=5887c9a25dbd99d3b127c07d42f2e4e1")
    //Call<MovieResponse> getPopularMovies();
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies1(
            @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MovieResponse> getMovies(
            @Query("api_key") String apiKey,
            @Query("query") String query);
}
