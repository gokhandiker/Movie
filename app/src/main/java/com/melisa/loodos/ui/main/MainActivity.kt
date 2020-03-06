package com.melisa.loodos.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.melisa.loodos.R
import com.melisa.loodos.data.model.Movie
import com.melisa.loodos.data.remote.MovieService
import com.melisa.loodos.data.remote.MovieServiceAPI
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    /**
     * 9c4f0a61 api key
     *
     * https://www.omdbapi.com/?apikey=9c4f0a61&t=s
     *
     */

    val movieServiceAPI : MovieServiceAPI by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val movieService : MovieService = movieServiceAPI.getMovieService()

        CoroutineScope(Dispatchers.IO).launch {
            val request =movieService.getMovies("9c4f0a61","star")

            withContext(Dispatchers.IO){

                try {
                    val response = request.await()
                    if (response.isSuccessful) {

                        var movie : Movie? = response.body()

                        Log.e("MainActicity",
                            movie?.title
                        )

                    } else {

                        Log.e("MainActicity",
                            "Error : Status ${response.code()} ")

                    }
                } catch (e: Exception) {
                    Log.e("MainActicity",
                        "Exception ${e.message}")

                }

            }
        }


    }
}
