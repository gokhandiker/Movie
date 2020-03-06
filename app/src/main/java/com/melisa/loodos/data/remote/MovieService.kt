package com.melisa.loodos.data.remote

import com.melisa.loodos.data.model.Movie
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/")
    fun getMovies(@Query("apikey") apiKey:String, @Query("t") Query:String):Deferred<Response<Movie>>
}