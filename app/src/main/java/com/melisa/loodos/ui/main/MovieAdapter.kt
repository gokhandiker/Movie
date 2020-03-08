package com.melisa.loodos.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.melisa.loodos.R
import com.melisa.loodos.data.domain.Movie
import kotlinx.android.synthetic.main.rv_movie_item.view.*
import kotlin.properties.Delegates

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    lateinit var list:MutableList<Movie>
    lateinit var context:Context
    lateinit var rv: View

    private var movieList: List<Movie> by Delegates.observable(emptyList()) { _,_,_->notifyDataSetChanged()}



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        rv = LayoutInflater.from(parent!!.context).inflate(R.layout.rv_movie_item,parent,false)
        return MovieViewHolder(rv)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        // Verify if position exists in list
        if (position != RecyclerView.NO_POSITION) {
            val movie : Movie = movieList[position]
            holder.bind(movie)
        }
    }

    // Update recyclerView's data
    fun updateData(newMovieList: List<Movie>) {
        movieList = newMovieList
    }


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            // Load images using Glide library
            Glide.with(itemView.context)
                .load(movie.poster)
                .centerCrop()
                .thumbnail()
                .into(itemView.img_movie_item_poster)
        }
    }
}