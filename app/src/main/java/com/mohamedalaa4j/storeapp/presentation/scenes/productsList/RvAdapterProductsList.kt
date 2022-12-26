package com.mohamedalaa4j.storeapp.presentation.scenes.productsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mohamedalaa4j.storeapp.R
import com.mohamedalaa4j.storeapp.data.models.received.ProductsModel
import com.mohamedalaa4j.storeapp.databinding.RvItemProductsListBinding

class RvAdapterProductsList(
    private val items: ProductsModel, private val listener: (productObject: ProductsModel.ProductsModelItem) -> Unit
) : RecyclerView.Adapter<RvAdapterProductsList.ViewHolder>() {


    class ViewHolder(binding: RvItemProductsListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val ivProduct = binding.ivProduct
        val tvTitle = binding.tvTitle
        val tvPrice = binding.tvPrice

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvItemProductsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]

        val context = holder.itemView.context

        holder.apply {
            tvTitle.text = item.title
            tvPrice.text = context.getString(R.string.price, item.price.toString())
            Glide.with(context).load(item.image).error(R.drawable.no_image).into(ivProduct)

        }


        holder.itemView.setOnClickListener {
            listener.invoke(item)
        }

    }


    override fun getItemCount(): Int {
        return items.size
    }
}