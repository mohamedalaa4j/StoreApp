package com.mohamedalaa4j.storeapp.data.repository

import com.mohamedalaa4j.storeapp.data.network.RetrofitInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface,
    private val ioDispatcher: CoroutineDispatcher
) : Repository {

    override suspend fun getProducts() = withContext(ioDispatcher) { retrofitInterface.getProducts() }

}