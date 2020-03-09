package com.melisa.loodos.ui.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.melisa.loodos.R
import com.melisa.loodos.data.domain.Movie
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(){

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

        val toolbar =
            findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)



        movieAdapter = MovieAdapter(arrayListOf())


        rv_movie.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_movie.adapter = movieAdapter


        initViewModel()
        setupAnimation()
    }


    fun setupAnimation(){
        val animation = findViewById<LottieAnimationView>(R.id.main_progressBar)
        animation.speed = 2.0F // How fast does the animation play
        animation.progress = 50F // Starts the animation from 50% of the beginning
        animation.addAnimatorUpdateListener {
            // Called everytime the frame of the animation changes
        }
        animation.repeatMode = LottieDrawable.RESTART // Restarts the animation (you can choose to reverse it as well)
        animation.cancelAnimation() // Cancels the animation
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        var searchView: SearchView? = null
        val myActionMenuItem: MenuItem = menu.findItem(R.id.action_search)
        searchView = myActionMenuItem.getActionView() as SearchView
        searchView.queryHint=getString(R.string.search_hint)
        val finalSearchView: SearchView? = searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.loadMovie(query!!)
                Log.e("onQueryTextSubmit",query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true

            }

        })
        return true
    }

    private fun initViewModel() {

        txt_info.text="Lütfen film aratınız"
        viewModel.movieList.observe(
            this,
            Observer { newMovie ->

                if (newMovie!!.title != null) {
                    var list: ArrayList<Movie> = arrayListOf()
                    list.add(newMovie!!)
                    movieAdapter.updateData(list)
                    txt_info.visibility=View.GONE
                } else {
                    Toast.makeText(this, "Film Bulunamadı!", Toast.LENGTH_SHORT)
                        .show()
                    var list: ArrayList<Movie> = arrayListOf()

                    movieAdapter.updateData(list)
                    txt_info.visibility=View.VISIBLE
                    txt_info.text="Aradığınız film Bulunamadı!"
                }

            })

        viewModel.showLoading.observe(
            this,
            Observer { showLoading -> Log.e("loading..", "state: " + showLoading!!)

                main_progressBar.visibility = if (showLoading!!) View.VISIBLE else View.GONE

                txt_info.visibility = View.GONE
            })

        viewModel.showError.observe(
            this,
            Observer { showError -> Log.e("showError..", "state: " + showError!!)
                txt_info.text="Hata oluştu!"
            })


    }



}
