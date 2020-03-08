package com.melisa.loodos.data.repository

import android.util.Log
import retrofit2.Response
import java.io.IOException

open class BaseRepository {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result : Output<T> = safeApiResult(call,errorMessage)
        var data : T? = null

        when(result) {
            is Output.Success ->
                data = result.data
            is Output.Error -> {
                Log.d("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }


        return data

    }

    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>, errorMessage: String) : Output<T>{
        val response = call.invoke()
        if(response.isSuccessful) return Output.Success(response.body()!!)

        return Output.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}