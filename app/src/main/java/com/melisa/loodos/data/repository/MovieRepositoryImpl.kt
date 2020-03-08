package com.melisa.loodos.data.repository

import com.melisa.loodos.data.domain.Movie
import com.melisa.loodos.data.remote.MovieService
import com.melisa.loodos.util.Constants
import kotlin.reflect.jvm.internal.impl.load.java.Constant

class MovieRepositoryImpl(private val movieService: MovieService) : MovieRepository {
    override suspend fun getMovie(searchText:String): Output<Movie> {
        return try {
            val result = movieService.getMovie("9c4f0a61", searchText).await()
            Output.Success(result)
        } catch (ex: Exception) {
            Output.Error(ex)
        }
    }

}