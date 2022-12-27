package com.mohamedalaa4j.storeapp.di

import com.mohamedalaa4j.storeapp.data.repository.Repository
import com.mohamedalaa4j.storeapp.data.repository.RepositoryImpl
import com.mohamedalaa4j.storeapp.domain.GetProductsListUseCase
import com.mohamedalaa4j.storeapp.domain.GetProductsListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class InterfacesImplModule {

    //Repository Provider
    @Binds
    abstract fun bindRepository(
        repositoryImpl: RepositoryImpl
    ): Repository

    //region UseCases
    @Binds
    abstract fun bindGetProductsListUseCase(
        getProductsListUseCaseImpl: GetProductsListUseCaseImpl
    ): GetProductsListUseCase

    //endregion

}