package com.myogardener.moviesapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myogardener.moviesapi.api.MoviesApiClient
import com.myogardener.moviesapi.model.Detail
import com.myogardener.moviesapi.model.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel: ViewModel() {

    private var resultt: MutableLiveData<Movies> = MutableLiveData()

    fun getResult(): LiveData<Movies> = resultt

    fun loadNews() {
        var apiClient = MoviesApiClient()
        val call = apiClient.getToprated()

        call.enqueue(object: Callback<Movies> {
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("Error>>>>",t.toString())// Change
            }

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                Log.d("res",response.body().toString())
                resultt.value = response.body()!!
            }

        })
    }
    private var result2: MutableLiveData<Detail> = MutableLiveData()

    fun getResult2(): LiveData<Detail> = result2

    fun loadNews2(movieId: String) {
        var apiClient = MoviesApiClient()
        val call = apiClient.getMoviesDetails(movieId)

        call.enqueue(object: Callback<Detail> {
            override fun onFailure(call: Call<Detail>, t: Throwable) {
                Log.d("Error>>>>",t.toString())// Change
            }

            override fun onResponse(call: Call<Detail>, response: Response<Detail>) {

                result2.value = response.body()!!
            }

        })
    }

}