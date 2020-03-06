package com.melisa.loodos.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieServiceAPI{

    fun getMovieService() : MovieService {

        val retrofit =Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return retrofit.create(MovieService::class.java)
    }


}