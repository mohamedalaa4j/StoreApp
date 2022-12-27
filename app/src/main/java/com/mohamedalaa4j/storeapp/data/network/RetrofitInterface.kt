package com.mohamedalaa4j.storeapp.data.network

import com.mohamedalaa4j.storeapp.data.models.received.ProductsModel
import com.mohamedalaa4j.storeapp.utilities.PRODUCTS_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitInterface {

    @GET(PRODUCTS_ENDPOINT)
    suspend fun getProducts(): Response<ProductsModel>
}