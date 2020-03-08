package com.melisa.loodos.data.repository

import com.melisa.loodos.data.domain.Movie

interface MovieRepository {
    suspend fun getMovie():Output<Movie>
}