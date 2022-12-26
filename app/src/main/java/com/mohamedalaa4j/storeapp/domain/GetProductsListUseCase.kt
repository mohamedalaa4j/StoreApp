package com.mohamedalaa4j.storeapp.domain

import com.mohamedalaa4j.storeapp.data.models.received.ProductsModel

interface GetProductsListUseCase {

    suspend operator fun invoke(): ProductsModel
}