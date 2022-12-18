package com.twaun95.presentation.ui.menubar

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.twaun95.presentation.R
import com.twaun95.presentation.base.BaseFragment
import com.twaun95.presentation.databinding.FragmentMenubarBinding
import com.twaun95.presentation.ui.main.MainActivityViewModel

class MenuBarFragment : BaseFragment<FragmentMenubarBinding, MenuBarFragmentViewModel>(R.layout.fragment_menubar) {

    override val fragmentVM: MenuBarFragmentViewModel by viewModels()
    private val activityVM: MainActivityViewModel by activityViewModels()

    override fun onDestroyView() {
        super.onDestroyView()

        activityVM.showMenuBar(false)
    }

    companion object {
        fun getInstance() : MenuBarFragment = MenuBarFragment()
    }
}