package com.melisa.loodos.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.melisa.loodos.data.remote.MovieService
import com.melisa.loodos.data.repository.MovieRepository
import com.melisa.loodos.data.repository.MovieRepositoryImpl
import com.melisa.loodos.ui.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


const val BASE_URL="https://www.omdbapi.com"


val appModule = module {

    single { createWebService<MovieService>(
        okHttpClient  = createHttpClient(),
        factory = RxJava2CallAdapterFactory.create(),
        baseUrl = BASE_URL
    ) }

    factory { MovieRepositoryImpl(movieService = get()) }


    viewModel { MainViewModel(movieRepository = get()) }


}





/* Returns a custom OkHttpClient instance with interceptor. Used for building Retrofit service */
fun createHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.readTimeout(5 * 60, TimeUnit.SECONDS)
    return client.addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        requestBuilder.header("Content-Type", "application/json")
        val request = requestBuilder.method(original.method, original.body).build()
        return@addInterceptor it.proceed(request)
    }.build()
}

/* function to build our Retrofit service */
inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    factory: CallAdapter.Factory, baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(factory)
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}