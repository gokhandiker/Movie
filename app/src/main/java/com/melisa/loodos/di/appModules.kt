package com.melisa.loodos.di

import com.melisa.loodos.data.remote.MovieServiceAPI
import org.koin.dsl.module

val appModule = module {

    single { MovieServiceAPI() }
}