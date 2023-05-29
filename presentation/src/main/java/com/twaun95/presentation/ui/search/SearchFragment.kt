package com.twaun95.presentation.ui.search

import android.view.MotionEvent
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.twaun95.presentation.R
import com.twaun95.presentation.adapter.PlaceListAdapter
import com.twaun95.presentation.base.BaseFragment
import com.twaun95.presentation.databinding.FragmentSearchBinding
import com.twaun95.presentation.ui.main.MainActivityViewModel
import com.twaun95.presentation.ui.webview.WebViewActivity
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

        binding.activity = activity
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
        }
    }

    private fun setRecyclerView() {
        binding.rvSearch.apply {
            adapter = placeAdapter.apply {
                onClick = { item ->
                    activityVM.onSelectItem(item)
                    activity?.onBackPressed()
                }
            }
        }
    }

    companion object {
        const val TAG = "SEARCH_FRAGMENT"

        fun getInstance(): SearchFragment = SearchFragment()
    }
}