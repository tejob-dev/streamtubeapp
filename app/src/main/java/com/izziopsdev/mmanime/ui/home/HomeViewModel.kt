package com.izziopsdev.mmanime.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.izziopsdev.mmanime.util.Helping

class HomeViewModel : ViewModel() {

    private val _url = MutableLiveData<String>()
    val url: LiveData<String> = _url

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        _url.value = Helping.urlMain
        _isLoading.value = true
    }

    fun onPageStarted() {
        _isLoading.value = true
    }

    fun onPageFinished() {
        _isLoading.value = false
    }
}
