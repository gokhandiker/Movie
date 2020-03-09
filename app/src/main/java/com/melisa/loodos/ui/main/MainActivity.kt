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

        viewModel.movieList.observe(
            this,
            Observer { newMovie ->

                if (newMovie!!.title != null) {
                    var list: ArrayList<Movie> = arrayListOf()
                    list.add(newMovie!!)
                    movieAdapter.updateData(list)
                } else {
                    Toast.makeText(this, "Film Bulunamadı!", Toast.LENGTH_SHORT)
                        .show()
                }

            })

        viewModel.showLoading.observe(
            this,
            Observer { showLoading -> Log.e("loading..", "state: " + showLoading!!)

                main_progressBar.visibility = if (showLoading!!) View.VISIBLE else View.GONE
            })

        viewModel.showError.observe(
            this,
            Observer { showError -> Log.e("showError..", "state: " + showError!!) })


    }



}
