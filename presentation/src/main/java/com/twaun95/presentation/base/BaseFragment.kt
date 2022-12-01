package com.twaun95.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel>(@LayoutRes private val layoutId: Int) : Fragment(layoutId){

    private var _binding: VB? = null
    val binding: VB
        get() = _binding ?: throw IllegalStateException("FAIL BINDING")

    abstract val fragmentVM: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setEvent()
        setObserver()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    open fun initView() {}
    open fun setEvent() {}
    open fun setObserver() {}
}