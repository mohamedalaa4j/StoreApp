package com.mohamedalaa4j.storeapp.presentation.scenes.productsList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        //to make StateFlow aware of Lifecycle of the MainActivity
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.productsListStateFlow.collect {
                    checkProductsListState(it)
                }
            }

        binding?.ivRefresh?.setOnClickListener { viewModel.getProductsList() }

        //override onBackPressed for doubleTapToExit
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Utilities.doubleTapToExit(activity!!)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //to avoid memory leak
        binding = null
    }

    private fun checkProductsListState(screenState: ScreenState<ProductsModel>) {

        when (screenState) {
            is ScreenState.InitialValue -> {
            }

            is ScreenState.Loading -> {
                Utilities.showProgressDialog(requireContext())
            }

            is ScreenState.Success -> {
                screenState.data?.let {
                    setupProductsListRV(it)
                }

                Utilities.cancelProgressDialog()
            }

            is ScreenState.Error -> {
                Toast.makeText(context, screenState.message, Toast.LENGTH_SHORT).show()
                Utilities.cancelProgressDialog()
            }
        }
    }

    private fun setupProductsListRV(data: ProductsModel) {
        binding?.rvProducts?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = RvAdapterProductsList(data) {
            findNavController().navigate(ProductsListFragmentDirections.actionProductsListFragmentToProductDetailsFragment(it))
        }

        //RecyclerView position state
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding?.rvProducts?.adapter = adapter

    }
}