package com.twaun95.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import com.twaun95.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(

) : BaseViewModel() {

    val inputSearch by lazy { MutableLiveData<String>("") }

}