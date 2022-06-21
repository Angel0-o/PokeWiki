package com.moragar.pokewiki.model.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonClient {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    private val okHttp: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logger)
    val instance : PokemonAPI by lazy {
        val client = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonAPI::class.java)
        client
    }
}