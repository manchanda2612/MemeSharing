package com.neeraj.memesharing

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("gimme")
    fun getMemes() : Call<MemeResponse>

    companion object {

        val BASE_URL = "https://meme-api.herokuapp.com/"

        fun create() : ApiInterface {
          val retrofit = Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build()

            return retrofit.create(ApiInterface::class.java)

        }
    }
}