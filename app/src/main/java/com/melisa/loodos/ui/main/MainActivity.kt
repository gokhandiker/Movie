package com.melisa.loodos.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.melisa.loodos.R
import com.melisa.loodos.data.domain.Movie
import com.melisa.loodos.data.remote.MovieService
import com.melisa.loodos.data.repository.MovieRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    /**
     * 9c4f0a61 api key
     *
     * https://www.omdbapi.com/?apikey=9c4f0a61&t=s
     *
     */

    private val viewModel: MainViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieAdapter = MovieAdapter(arrayListOf())


        rv_movie.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_movie.adapter = movieAdapter


        initViewModel()
    }

    private fun initViewModel() {

        viewModel.movieList.observe(
            this,
            Observer { newMovie ->
            var list : ArrayList<Movie> = arrayListOf()
                list.add(newMovie!!)
                movieAdapter.updateData(list)
            })

        viewModel.showLoading.observe(
            this,
            Observer { showLoading -> Log.e("loading..", "state: " + showLoading!!) })

        viewModel.showError.observe(
            this,
            Observer { showError -> Log.e("showError..", "state: " + showError!!) })

        viewModel.loadMovie()
    }


}
