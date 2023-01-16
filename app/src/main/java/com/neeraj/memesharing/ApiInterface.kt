package com.neeraj.memesharing

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("gimme")
    fun getMemes() : Call<MemeResponse>

    companion object {

        private const val BASE_URL = "https://meme-api.com/"

        fun create() : ApiInterface {
          return Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build()
              .create(ApiInterface::class.java)
        }
    }
}