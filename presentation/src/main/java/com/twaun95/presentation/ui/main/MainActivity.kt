package com.twaun95.presentation.ui.main

import android.graphics.Rect
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import com.twaun95.domain.entity.Place
import com.twaun95.presentation.R
import com.twaun95.presentation.base.BaseActivity
import com.twaun95.presentation.databinding.ActivityMainBinding
import com.twaun95.presentation.model.MapViewStatus
import com.twaun95.presentation.ui.menubar.MenuBarFragment
import com.twaun95.presentation.ui.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.CameraUpdate
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

        binding.buttonClose.setOnClickListener {
            viewModel.updateMapViewStatus(MapViewStatus.CURRENT_LOCATION)
        }
    }

    override fun setObserver() {
        super.setObserver()

        viewModel.mapViewStatus.observe(this) {
            Timber.d("$it")
            when(it) {
                MapViewStatus.CURRENT_LOCATION -> {
                    // 현재 주소 확인 후 상단바에 표시
                    mapView.removeAllPOIItems()
                }
                MapViewStatus.MARKER -> {
                    // 마커 주소 상단바에 표시, X 표시
                }
                else -> {}
            }
        }

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

        viewModel.selectedPace.observe(this) {
            Timber.d("selected $it")
            addMarker(it)
            viewModel.updateMapViewStatus(MapViewStatus.MARKER)
        }
    }

    private fun addMarker(item: Place) {
        val marker = MapPOIItem()
        marker.apply {
            itemName = item.name
            mapPoint = MapPoint.mapPointWithGeoCoord(item.y.toDouble(), item.x.toDouble())
            markerType = MapPOIItem.MarkerType.CustomImage
            customImageResourceId = R.drawable.marker_red
            selectedMarkerType = MapPOIItem.MarkerType.CustomImage
            customSelectedImageResourceId = R.drawable.marker_red
            isCustomImageAutoscale = false      // 커스텀 마커 이미지 크기 자동 조정
        }
        mapView.addPOIItem(marker)

        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(item.y.toDouble(), item.x.toDouble()),2,true)
        // TODO  해당 위치로 지동 중심 이동, 줌

        // TODO  맡커 클릭시 말풍선 나오고 다시한번 누르면 BottomSheetDialog 띄우기
    }

    private fun setMap() {
        mapView = MapView(this)
        binding.viewMap.addView(mapView)
        mapView.apply {
//            setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), true)
            currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
        }
//        mapView.mapType = MapView.MapType.Standard

        mapView.setZoomLevel(5, true) // level 클수록 더 넓게 보임
//        val marker = MapPOIItem()
//        marker.apply {
//            itemName = "서울시청"
//            mapPoint = MapPoint.mapPointWithGeoCoord(37.5666805, 126.9784147)
//            markerType = MapPOIItem.MarkerType.CustomImage
//            customImageResourceId = R.drawable.marker_red
//            selectedMarkerType = MapPOIItem.MarkerType.CustomImage
//            customSelectedImageResourceId = R.drawable.marker_red
//            isCustomImageAutoscale = false      // 커스텀 마커 이미지 크기 자동 조정
////            setCustomImageAnchor(0.5f, 1.0f)
//        }
//        mapView.addPOIItem(marker)

//        val marker2 = MapPOIItem()
//        marker2.apply {
//            itemName = "마커2"
//            mapPoint = MapPoint.mapPointWithGeoCoord(37.4, 126.9784147)
//            markerType = MapPOIItem.MarkerType.CustomImage
//            customImageResourceId = R.drawable.marker_red
//            selectedMarkerType = MapPOIItem.MarkerType.CustomImage
//            customSelectedImageResourceId = R.drawable.marker_red
//            isCustomImageAutoscale = false
//        }
//
//        val markerArray = arrayOf(marker, marker2)
//
//        mapView.addPOIItems(markerArray)
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