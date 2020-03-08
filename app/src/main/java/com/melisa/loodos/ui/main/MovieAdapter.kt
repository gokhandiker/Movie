package com.melisa.loodos.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.melisa.loodos.R
import com.melisa.loodos.data.domain.Movie
import kotlinx.android.synthetic.main.rv_movie_item.view.*
import kotlin.properties.Delegates

class MovieAdapter(var movieList: ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View  = LayoutInflater.from(parent!!.context).inflate(R.layout.rv_movie_item,parent,false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
       return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {


            val movie : Movie = movieList[position]
            holder.bind(movie)

    }

    // Update recyclerView's data
    fun updateData(newMovieList: ArrayList<Movie>) {
        movieList.clear()
        movieList.addAll(newMovieList)

        notifyDataSetChanged()
    }


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val movieTitle = itemView.txt_movie_item_title
        fun bind(movie: Movie) {
            // Load images using Glide library


            Glide.with(itemView.context)
                .load(movie.poster)
                .centerCrop()
                .thumbnail()
                .into(itemView.img_movie_item_poster)


            Log.e("MovieViewHolder: ",movie.title)
            movieTitle.text = movie.title
        }
    }
}