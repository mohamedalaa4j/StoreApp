package com.mohamedalaa4j.storeapp.data.repository

import com.mohamedalaa4j.storeapp.data.models.received.ProductsModel
import retrofit2.Response

interface Repository {
    suspend fun getProducts(): Response<ProductsModel>
}