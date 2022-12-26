package com.mohamedalaa4j.storeapp.presentation.scenes.productsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamedalaa4j.storeapp.data.models.received.ProductsModel
import com.mohamedalaa4j.storeapp.domain.GetProductsListUseCase
import com.mohamedalaa4j.storeapp.utilities.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsListVM @Inject constructor(private val getProductsListUseCase: GetProductsListUseCase) : ViewModel() {

    private var _productsListStateFlow = MutableStateFlow<ScreenState<ProductsModel>>(ScreenState.InitialValue(null))

    val productsListStateFlow: StateFlow<ScreenState<ProductsModel>>
        get() = _productsListStateFlow

    fun getProductsList() {
        viewModelScope.launch {
            _productsListStateFlow.emit(ScreenState.Loading(null))

            try {
                _productsListStateFlow.emit(ScreenState.Success(getProductsListUseCase()))
            } catch (e: Exception) {
                _productsListStateFlow.emit(ScreenState.Error(e.message.toString(), null))
            }
        }
    }

    init {
        getProductsList()
    }

}