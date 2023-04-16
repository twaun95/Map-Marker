package com.twaun95.presentation.ui.search

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.twaun95.presentation.R
import com.twaun95.presentation.adapter.PlaceListAdapter
import com.twaun95.presentation.base.BaseFragment
import com.twaun95.presentation.databinding.FragmentSearchBinding
import com.twaun95.presentation.ui.main.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchFragmentViewModel>(R.layout.fragment_search) {
    override val fragmentVM: SearchFragmentViewModel by viewModels()
    private val activityVM: MainActivityViewModel by activityViewModels()

    private val placeAdapter by lazy { PlaceListAdapter() }

    override fun initView() {
        super.initView()

        binding.fragmentVM = this.fragmentVM
        binding.activityVM = this.activityVM

        setRecyclerView()
    }

    override fun setObserver() {
        super.setObserver()

        fragmentVM.placeList
            .onEach {
                placeAdapter.submitList(it)
            }
            .launchIn(this.lifecycleScope)
    }

    override fun setEvent() {
        super.setEvent()

        binding.btnBack.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.btnSearch.setOnClickListener {
            fragmentVM.onSearch()
//            activity?.onBackPressed()
        }
    }

    private fun setRecyclerView() {
        binding.rvSearch.apply {
            adapter = placeAdapter.apply {
                onClick = { item ->

                }
            }
        }
    }

    companion object {
        val TAG = "SEARCH_FRAGMENT"

        fun getInstance(): SearchFragment = SearchFragment()
    }
}