package com.melisa.loodos.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.melisa.loodos.data.domain.Movie
import com.melisa.loodos.data.repository.MovieRepository
import com.melisa.loodos.data.repository.MovieRepositoryImpl
import com.melisa.loodos.data.repository.Output
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel(private val movieRepository: MovieRepositoryImpl) : ViewModel(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val showLoading = MutableLiveData<Boolean>()
    val movieList = MutableLiveData<Movie>()
    val showError = SingleLiveEvent<String>()


    fun loadMovie() {
        showLoading.value = true

        launch {

            val result = withContext(Dispatchers.IO) { movieRepository.getMovie() }

            showLoading.value = false

            when (result) {
                is Output.Success -> movieList.value=result.data
                is Output.Error -> showError.value = result.exception.message
            }

        }
    }


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}