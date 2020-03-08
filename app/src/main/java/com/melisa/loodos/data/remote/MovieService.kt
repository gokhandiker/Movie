package com.melisa.loodos.data.remote

import com.melisa.loodos.data.domain.Movie
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieService {

    @GET("/")
    @Headers("Content-type: application/json")
    fun getMovie(@Query("apikey") apiKey:String, @Query("t") Query:String):Deferred<Movie>
}