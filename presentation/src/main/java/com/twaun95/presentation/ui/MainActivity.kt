package com.twaun95.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.twaun95.presentation.R
import com.twaun95.presentation.base.BaseActivity
import com.twaun95.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun initView() {
        super.initView()

        setMap()
    }

    override fun setEvent() {
        super.setEvent()
    }

    override fun setObserver() {
        super.setObserver()
    }

    private fun setMap() {
        val mapView = MapView(this).apply {
//            setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), true)
            currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
            setCurrentLocationEventListener(object : MapView.CurrentLocationEventListener{
                override fun onCurrentLocationUpdate(p0: MapView, p1: MapPoint, p2: Float) {
                    Timber.d("p0: $p0")
                    Timber.d("p1: $p1")
                    Timber.d("p2: $p2")

                }

                override fun onCurrentLocationDeviceHeadingUpdate(p0: MapView?, p1: Float) {
                    TODO("Not yet implemented")
                }

                override fun onCurrentLocationUpdateFailed(p0: MapView?) {
                    TODO("Not yet implemented")
                }

                override fun onCurrentLocationUpdateCancelled(p0: MapView?) {
                    TODO("Not yet implemented")
                }

            })
        }
        binding.viewMap.addView(mapView)
    }
}