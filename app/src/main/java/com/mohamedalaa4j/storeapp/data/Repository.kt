package com.mohamedalaa4j.storeapp.data

import com.mohamedalaa4j.storeapp.data.network.RetrofitInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(
    private val retrofitInterface: RetrofitInterface,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun getProducts() = withContext(ioDispatcher) { retrofitInterface.getProducts() }

}