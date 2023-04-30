package com.twaun95.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.twaun95.domain.entity.Place
import com.twaun95.domain.usecase.GetPlaceByKeywordUseCase
import com.twaun95.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : BaseViewModel() {

    private val _isTrackingMode = MutableLiveData<Boolean>(false)
    val isTrackingMode: LiveData<Boolean> get() = _isTrackingMode

    val currentLocation by lazy { MutableLiveData<String>("") }

    private val _selectedPace = MutableLiveData<Place>()
    val selectedPace: LiveData<Place> get() = _selectedPace

    fun trackingModeOnOff() {
        _isTrackingMode.value = _isTrackingMode.value == false
    }

    fun onSelectItem(item: Place) {
        _selectedPace.value = item
        currentLocation.value = item.address
    }
}