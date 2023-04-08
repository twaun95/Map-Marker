package com.twaun95.presentation.ui.main

import android.graphics.Rect
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import com.twaun95.presentation.R
import com.twaun95.presentation.base.BaseActivity
import com.twaun95.presentation.databinding.ActivityMainBinding
import com.twaun95.presentation.ui.menubar.MenuBarFragment
import com.twaun95.presentation.ui.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import timber.log.Timber


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainActivityViewModel>()

    private lateinit var mapView: MapView

    override fun initView() {
        super.initView()


        binding.viewModel = viewModel

        setMap()
    }

    override fun setEvent() {
        super.setEvent()

        binding.buttonMenubar.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_enter_from_left, R.anim.slide_exit_to_left, R.anim.slide_enter_from_left,R.anim.slide_exit_to_left)
                .add(R.id.layout_frame_popup, MenuBarFragment.getInstance())
                .addToBackStack(null)
                .commit()
        }

        binding.textSearch.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_main, SearchFragment.getInstance(), SearchFragment.TAG)
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

        viewModel.currentLocation.observe(this) {
            Timber.d(it)
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

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm: InputMethodManager =
                        getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}