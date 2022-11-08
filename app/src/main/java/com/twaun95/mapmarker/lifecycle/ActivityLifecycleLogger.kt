package com.twaun95.mapmarker.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber
import javax.inject.Inject

class ActivityLifecycleLogger @Inject constructor() : Application.ActivityLifecycleCallbacks {

    private val fragmentLogTracker = FragmentLifecycleLogger()

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        if (activity is AppCompatActivity) {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                fragmentLogTracker,
                true
            )
        }

        Timber.d("%s%s--onCreate", activity::class.java.simpleName, "(${activity.hashCode()})")
    }

    override fun onActivityStarted(activity: Activity) {
        Timber.d("%s%s--onStart", activity::class.java.simpleName, "(${activity.hashCode()})")
    }

    override fun onActivityResumed(activity: Activity) {
        Timber.d("%s%s--onResume", activity::class.java.simpleName, "(${activity.hashCode()})")
    }

    override fun onActivityPaused(activity: Activity) {
        Timber.d("%s%s--onPause", activity::class.java.simpleName, "(${activity.hashCode()})")
    }

    override fun onActivityStopped(activity: Activity) {
        Timber.d("%s%s--onStop", activity::class.java.simpleName, "(${activity.hashCode()})")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
//        Timber.d(
//            "%s%s--onSaveInstanceState",
//            activity::class.java.simpleName,
//            "(${activity.hashCode()})"
//        )
    }

    override fun onActivityDestroyed(activity: Activity) {
        Timber.d("%s%s--onDestroy", activity::class.java.simpleName, "(${activity.hashCode()})")
    }
}