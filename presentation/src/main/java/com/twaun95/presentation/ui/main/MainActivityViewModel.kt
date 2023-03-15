package com.twaun95.presentation.ui.main

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.twaun95.presentation.base.BaseViewModel
import com.twaun95.presentation.model.ViewState
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

    private val _viewState = MutableLiveData<ViewState>(ViewState.MAP)
    val viewState: LiveData<ViewState>
        get() = _viewState

    private val _isSearching = MutableLiveData<Boolean>(false)
    val isSearching: LiveData<Boolean>
        get() = _isSearching

    val searchText by lazy { MutableLiveData<String>("") }


    fun trackingModeOnOff() {
        _isTrackingMode.value = _isTrackingMode.value == false
    }

    fun showMenuBar(isMenuBar: Boolean) {
        _isMenuBar.value = isMenuBar
    }

    fun setViewState(state: ViewState) {
        _viewState.value = state
    }

    fun setSearchingMode(isSearching: Boolean) {
        _isSearching.value = isSearching
    }
}