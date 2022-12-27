package com.mohamedalaa4j.storeapp.presentation.scenes.splashScreen

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashVM : ViewModel() {

    private var _navigate = MutableStateFlow(false)
    val navigate: StateFlow<Boolean>
        get() = _navigate

    private fun delay() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModelScope.launch {
                _navigate.emit(true)
            }
        }, 2000)
    }

    fun navigationDone() {
        viewModelScope.launch {
            _navigate.emit(false)
        }
    }

    init {
        delay()
    }
}