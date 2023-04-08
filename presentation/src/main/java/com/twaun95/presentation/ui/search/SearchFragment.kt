package com.twaun95.presentation.ui.search

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.twaun95.presentation.R
import com.twaun95.presentation.base.BaseFragment
import com.twaun95.presentation.databinding.FragmentSearchBinding
import com.twaun95.presentation.ui.main.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchFragmentViewModel>(R.layout.fragment_search) {
    override val fragmentVM: SearchFragmentViewModel by viewModels()
    private val activityVM: MainActivityViewModel by activityViewModels()

    override fun initView() {
        super.initView()

        binding.fragmentVM = this.fragmentVM
        binding.activityVM = this.activityVM
    }

    override fun setObserver() {
        super.setObserver()
    }

    override fun setEvent() {
        super.setEvent()

        binding.btnBack.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.btnSearch.setOnClickListener {
            activityVM.onSearch(fragmentVM.inputSearch.value.toString())
            activity?.onBackPressed()
        }
    }

    companion object {
        val TAG = "SEARCH_FRAGMENT"

        fun getInstance(): SearchFragment = SearchFragment()
    }
}