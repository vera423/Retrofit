package com.example.testretrofit.json_mapper;

import java.util.List;

public class MovieResponse {
    //URL https://api.themoviedb.org/3/movie/popular?api_key=5887c9a25dbd99d3b127c07d42f2e4e1
    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
