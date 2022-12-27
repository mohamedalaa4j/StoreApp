package com.mohamedalaa4j.storeapp.presentation.scenes.productDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mohamedalaa4j.storeapp.R
import com.mohamedalaa4j.storeapp.databinding.FragmentProductDetailsBinding

class ProductDetailsFragment : Fragment(R.layout.fragment_product_details) {
    private var binding: FragmentProductDetailsBinding? = null

    private val args by navArgs<ProductDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailsBinding.bind(view)

        bindTheProductViews()

        binding?.ivBack?.setOnClickListener { activity?.onBackPressed() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //to avoid memory leak
        binding = null
    }

    private fun bindTheProductViews(){
        binding?.tvTitle?.text = args.productObject.title.toString()

        Glide.with(requireContext()).load(args.productObject.image.toString()).error(R.drawable.no_image).into(binding?.imageView!!)

        binding?.tvDescription?.text = args.productObject.description.toString()

        val rate = args.productObject.rating?.rate.toString()
        binding?.ratingBar?.rating = rate.toFloat()

        val price = args.productObject.price.toString()
        binding?.tvPriceValue?.text = getString(R.string.price,price)
    }
}