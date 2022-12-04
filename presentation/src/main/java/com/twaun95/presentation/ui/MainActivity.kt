package com.twaun95.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.twaun95.presentation.R
import com.twaun95.presentation.base.BaseActivity
import com.twaun95.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapView
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun initView() {
        super.initView()

        val mapView = MapView(this)
        binding.viewMap.addView(mapView)
    }

    override fun setEvent() {
        super.setEvent()
    }

    override fun setObserver() {
        super.setObserver()
    }
}