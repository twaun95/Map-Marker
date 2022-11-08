package com.twaun95.mapmarker

import android.app.Application
import com.twaun95.mapmarker.lifecycle.ActivityLifecycleLogger
import com.twaun95.mapmarker.lifecycle.FragmentLifecycleLogger
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MMApplication : Application() {

    @Inject
    lateinit var activityLifecycleLogger: ActivityLifecycleLogger

    override fun onCreate() {
        super.onCreate()
        initLogger()
        registerLifecycleCallbacks()
    }

    private fun initLogger() {
        Timber.plant(Timber.DebugTree())
    }

    private fun registerLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(activityLifecycleLogger)
    }
}