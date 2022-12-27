package com.mohamedalaa4j.storeapp.presentation.scenes.splashScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mohamedalaa4j.storeapp.R
import com.mohamedalaa4j.storeapp.databinding.FragmentSplashBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private var binding: FragmentSplashBinding? = null

    private val viewModel: SplashVM by lazy {
        ViewModelProvider(this)[SplashVM::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

        lifecycleScope.launchWhenStarted {
            viewModel.navigate.collect {
                if (it)
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToProductsListFragment())
                viewModel.navigationDone()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //to avoid memory leak
        binding = null
    }
}