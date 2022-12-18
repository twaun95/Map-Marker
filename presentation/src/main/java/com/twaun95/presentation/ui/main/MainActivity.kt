package com.twaun95.presentation.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.twaun95.presentation.R
import com.twaun95.presentation.base.BaseActivity
import com.twaun95.presentation.databinding.ActivityMainBinding
import com.twaun95.presentation.ui.menubar.MenuBarFragment
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import timber.log.Timber
import java.util.logging.Logger


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainActivityViewModel>()

    private lateinit var mapView: MapView

    override fun initView() {
        super.initView()

        binding.viewModel = viewModel

        setPermission()
        setMap()
    }

    override fun setEvent() {
        super.setEvent()

        binding.buttonMenubar.setOnClickListener {
            this.viewModel.showMenuBar(true)
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_enter_from_left, R.anim.slide_exit_to_left, R.anim.slide_enter_from_left,R.anim.slide_exit_to_left)
                .add(R.id.layout_frame_popup, MenuBarFragment.getInstance())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun setObserver() {
        super.setObserver()

        viewModel.isTrackingMode.observe(this) {
            if (it) {
                mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
            } else {
                mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff
            }
        }
    }

    private fun setPermission() {
        val permissionCheck: Int =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

        if (permissionCheck == PackageManager.PERMISSION_DENIED) { //포그라운드 위치 권한 확인
            //위치 권한 요청
            ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION), 0)
        }

        val permissionCheck2: Int =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)
            } else {
                TODO("VERSION.SDK_INT < Q")
            }

        if (permissionCheck2 == PackageManager.PERMISSION_DENIED) { //백그라운드 위치 권한 확인
            //위치 권한 요청
            ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.ACCESS_BACKGROUND_LOCATION), 0)
        }
    }

    private fun setMap() {
        mapView = MapView(this)
        binding.viewMap.addView(mapView)
//        mapView.apply {
//            setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), true)
//            currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
//        }
//        mapView.mapType = MapView.MapType.Standard

        mapView.setZoomLevel(5, true) // level 클수록 더 넓게 보임
        val marker = MapPOIItem()
        marker.apply {
            itemName = "서울시청"
            mapPoint = MapPoint.mapPointWithGeoCoord(37.5666805, 126.9784147)
            markerType = MapPOIItem.MarkerType.CustomImage
            customImageResourceId = R.drawable.marker_red
            selectedMarkerType = MapPOIItem.MarkerType.CustomImage
            customSelectedImageResourceId = R.drawable.marker_red
            isCustomImageAutoscale = false      // 커스텀 마커 이미지 크기 자동 조정
//            setCustomImageAnchor(0.5f, 1.0f)
        }
        mapView.addPOIItem(marker)

        val marker2 = MapPOIItem()
        marker2.apply {
            itemName = "마커2"
            mapPoint = MapPoint.mapPointWithGeoCoord(37.4, 126.9784147)
            markerType = MapPOIItem.MarkerType.CustomImage
            customImageResourceId = R.drawable.marker_red
            selectedMarkerType = MapPOIItem.MarkerType.CustomImage
            customSelectedImageResourceId = R.drawable.marker_red
            isCustomImageAutoscale = false
        }

        val markerArray = arrayOf(marker, marker2)

        mapView.addPOIItems(markerArray)
    }
}