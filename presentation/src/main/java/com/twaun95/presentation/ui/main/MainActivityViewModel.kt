package com.twaun95.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.twaun95.domain.usecase.GetPlaceByKeywordUseCase
import com.twaun95.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getPlaceByKeywordUseCase : GetPlaceByKeywordUseCase
) : BaseViewModel() {

    private val _isTrackingMode = MutableLiveData<Boolean>(false)
    val isTrackingMode: LiveData<Boolean> get() = _isTrackingMode

    val currentLocation by lazy { MutableLiveData<String>("") }

    fun trackingModeOnOff() {
        _isTrackingMode.value = _isTrackingMode.value == false
    }

    fun onSearch(text: String) {
        currentLocation.postValue(text)
        viewModelScope.launch {
            startLoading()
            kotlin.runCatching {
                getPlaceByKeywordUseCase.invoke("커피")
            }.onSuccess {
                Timber.d("SUCCESS")
            }.onFailure {
                Timber.d("FAIL")
            }
            stopLoading()
        }
    }
}