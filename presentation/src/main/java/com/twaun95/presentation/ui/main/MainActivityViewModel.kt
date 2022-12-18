package com.twaun95.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.twaun95.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : BaseViewModel() {

    private val _isTrackingMode = MutableLiveData<Boolean>(false)
    val isTrackingMode: LiveData<Boolean>
        get() = _isTrackingMode

    private val _isMenuBar = MutableLiveData<Boolean>(false)
    val isMenuBar: LiveData<Boolean>
        get() = _isMenuBar

    fun trackingModeOnOff() {
        _isTrackingMode.value = _isTrackingMode.value == false
    }

    fun showMenuBar(isMenuBar: Boolean) {
        _isMenuBar.value = isMenuBar
    }
}