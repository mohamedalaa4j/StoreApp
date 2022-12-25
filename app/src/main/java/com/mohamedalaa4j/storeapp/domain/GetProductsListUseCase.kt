package com.mohamedalaa4j.storeapp.domain

import com.mohamedalaa4j.storeapp.data.Repository
import com.mohamedalaa4j.storeapp.data.models.received.ProductsModel
import javax.inject.Inject

class GetProductsListUseCase@Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): ProductsModel? {
        return repository.getProducts().body()
    }
}