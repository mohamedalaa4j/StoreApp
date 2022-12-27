package com.mohamedalaa4j.storeapp.data.network

import com.mohamedalaa4j.storeapp.utilities.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitObject {

    private val retrofitInitialization: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofit: RetrofitInterface by lazy {
        retrofitInitialization.create(RetrofitInterface::class.java)
    }
}