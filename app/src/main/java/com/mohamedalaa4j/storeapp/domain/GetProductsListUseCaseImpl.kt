package com.mohamedalaa4j.storeapp.domain

import com.mohamedalaa4j.storeapp.data.repository.RepositoryImpl
import com.mohamedalaa4j.storeapp.data.models.received.ProductsModel
import javax.inject.Inject

class GetProductsListUseCaseImpl @Inject constructor(private val repositoryImpl: RepositoryImpl):GetProductsListUseCase {
    override suspend fun invoke(): ProductsModel {
        return repositoryImpl.getProducts().body()!!
    }
}