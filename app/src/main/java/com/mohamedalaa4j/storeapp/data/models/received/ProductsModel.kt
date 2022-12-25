package com.mohamedalaa4j.storeapp.data.models.received


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ProductsModel : ArrayList<ProductsModel.ProductsModelItem>(), Parcelable {
    @Parcelize
    data class ProductsModelItem(
        val category: String?,
        val description: String?,
        val id: Int?,
        val image: String?,
        val price: Double?,
        val rating: Rating?,
        val title: String?
    ) : Parcelable {
        @Parcelize
        data class Rating(
            val count: Int?,
            val rate: Double?
        ) : Parcelable
    }
}