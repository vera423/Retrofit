package com.example.testretrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.testretrofit.json_mapper.Movie;
import com.example.testretrofit.json_mapper.MovieResponse;
import com.example.testretrofit.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String API_KEY = "5887c9a25dbd99d3b127c07d42f2e4e1";

    private Button btnGetMovies;

    private EditText editTextSearch;
    private Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetMovies = findViewById(R.id.buttonPopular);

        btnGetMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Me traigo los datos
                Call<MovieResponse> call = RetrofitClient.getInstance().
                        getPopularMovies1(API_KEY);

                call.enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if (response.isSuccessful()) {
                            List<Movie> movies = response.body().getResults();
                            // Procesa y muestra las películas aquí
                            for (Movie myMovie:movies){
                                Toast.makeText(MainActivity.this, "Movie:"+myMovie.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        // Maneja el error aquí


                    }
                });
            }
        });

        // Conectar el EditText y el botón desde el layout
        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = editTextSearch.getText().toString().trim();

                if (!query.isEmpty()) {
                    searchMovies(query);
                } else {
                    Toast.makeText(MainActivity.this, "Ingresa un término de búsqueda", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Método para buscar películas
    private void searchMovies(String query) {
        Call<MovieResponse> call = RetrofitClient.getInstance().getMovies(API_KEY, query);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    List<Movie> movies = response.body().getResults();
                    // Procesa y muestra las películas aquí
                    for (Movie movie : movies) {
                        Toast.makeText(MainActivity.this, "Película: " + movie.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error en la respuesta: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                // Manejar el error aquí
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Conectar el botón desde el layout
        btnGetMovies = findViewById(R.id.buttonPopular);

        btnGetMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Me traigo los datos
                Call<MovieResponse> call = RetrofitClient.getInstance().
                        getPopularMovies1(API_KEY);

                call.enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if (response.isSuccessful()) {
                            List<Movie> movies = response.body().getResults();
                            // Procesa y muestra las películas aquí
                            for (Movie myMovie:movies){
                                Toast.makeText(MainActivity.this, "Movie:"+myMovie.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        // Maneja el error aquí


                    }
                });
            }
        });

*/

    }
