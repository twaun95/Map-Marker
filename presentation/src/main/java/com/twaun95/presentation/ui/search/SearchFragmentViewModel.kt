package com.twaun95.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.twaun95.domain.entity.Place
import com.twaun95.domain.usecase.GetPlaceByKeywordUseCase
import com.twaun95.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val getPlaceByKeywordUseCase : GetPlaceByKeywordUseCase
) : BaseViewModel() {

    val inputSearch by lazy { MutableLiveData<String>("") }

    private val _placeList = MutableStateFlow(emptyList<Place>())
    val placeList: StateFlow<List<Place>> get() = _placeList

    fun onSearch() {
//        currentLocation.postValue(text)
        viewModelScope.launch {
            startLoading()
            kotlin.runCatching {
                getPlaceByKeywordUseCase.invoke(inputSearch.value.toString())
            }.onSuccess {
                _placeList.value = it
                Timber.d("SUCCESS")
                Timber.d("first: ${it.first()}")
            }.onFailure {
                Timber.d("FAIL: ${it.message}")
            }
            stopLoading()
        }
    }
}