package com.myogardener.moviesapi.api

import com.myogardener.moviesapi.model.Detail
import com.myogardener.moviesapi.model.Movies
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesApiClient {

    private val BASE_URL = "https://api.themoviedb.org/3/movie/"

    companion object{
        val API_KEY = "f6b0c0999ad7091ca23fc4f01396aef0"
    }

    private val apiInterface: MoviesApiInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(MoviesApiInterface::class.java)
    }
    fun getToprated(): Call<Movies> {
        return apiInterface.getTopRated(API_KEY)
    }
    fun getMoviesDetails(
        movieId: String
    ): Call<Detail> {
        return apiInterface.getMoviesDetail(movieId.toInt(),API_KEY)
    }
}