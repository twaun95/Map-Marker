package com.twaun95.presentation.base

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VB: ViewDataBinding>(@LayoutRes private val layoutId: Int) : AppCompatActivity() {

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this

        initView()
        setEvent()
        setObserver()
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }

    open fun initView() {
        setPermission()
    }
    open fun setEvent() {}
    open fun setObserver() {}


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
}