package com.mohamedalaa4j.storeapp.presentation.scenes.productsList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamedalaa4j.storeapp.R
import com.mohamedalaa4j.storeapp.data.models.received.ProductsModel
import com.mohamedalaa4j.storeapp.databinding.FragmentProductsListBinding
import com.mohamedalaa4j.storeapp.utilities.ScreenState
import com.mohamedalaa4j.storeapp.utilities.Utilities
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsListFragment : Fragment(R.layout.fragment_products_list) {
    private var binding: FragmentProductsListBinding? = null

    private val viewModel: ProductsListVM by lazy {
        ViewModelProvider(this)[ProductsListVM::class.java]
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductsListBinding.bind(view)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.productsListStateFlow.collect {
                parseProductsListStateFlow(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun parseProductsListStateFlow(screenState: ScreenState<ProductsModel>) {

        when (screenState) {

            is ScreenState.InitialValue -> {
            }

            is ScreenState.Loading -> {
                Utilities.showProgressDialog(requireContext())
            }

            is ScreenState.Success -> {
                    screenState.data?.let {
                        setupProductsRV(it)
                    }

                    Utilities.cancelProgressDialog()
            }

            is ScreenState.Error -> {
                Toast.makeText(context, screenState.message, Toast.LENGTH_SHORT).show()
                Utilities.cancelProgressDialog()
            }
        }
    }

    private fun setupProductsRV(data: ProductsModel) {
        binding?.rvProducts?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        // adapter
        val adapter = RvAdapterProductsList(data)
        binding?.rvProducts?.adapter = adapter
    }

}