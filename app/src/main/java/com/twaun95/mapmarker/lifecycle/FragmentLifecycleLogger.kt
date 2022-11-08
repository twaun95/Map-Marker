package com.twaun95.mapmarker.lifecycle

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import timber.log.Timber
import javax.inject.Inject

class FragmentLifecycleLogger @Inject constructor() : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
        super.onFragmentAttached(fm, f, context)
        Timber.d("%s%s--onAttach", f::class.java.simpleName, "(${f.hashCode()})")
    }

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        super.onFragmentCreated(fm, f, savedInstanceState)
        Timber.d("%s%s--onCreate", f::class.java.simpleName, "(${f.hashCode()})")
    }

    override fun onFragmentViewCreated(
        fm: FragmentManager,
        f: Fragment,
        v: View,
        savedInstanceState: Bundle?
    ) {
        super.onFragmentViewCreated(fm, f, v, savedInstanceState)
        Timber.d("%s%s--onViewCreated", f::class.java.simpleName, "(${f.hashCode()})")
    }

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        super.onFragmentStarted(fm, f)
        Timber.d("%s%s--onStart", f::class.java.simpleName, "(${f.hashCode()})")
    }

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        super.onFragmentResumed(fm, f)
        Timber.d("%s%s--onResume", f::class.java.simpleName, "(${f.hashCode()})")
    }

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        super.onFragmentPaused(fm, f)
        Timber.d("%s%s--onPause", f::class.java.simpleName, "(${f.hashCode()})")
    }

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
        super.onFragmentStopped(fm, f)
        Timber.d("%s%s--onStop", f::class.java.simpleName, "(${f.hashCode()})")
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
        super.onFragmentViewDestroyed(fm, f)
        Timber.d("%s%s--onViewDestroyed", f::class.java.simpleName, "(${f.hashCode()})")
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        super.onFragmentDestroyed(fm, f)
        Timber.d("%s%s--onDestroy", f::class.java.simpleName, "(${f.hashCode()})")
    }

    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
        super.onFragmentDetached(fm, f)
        Timber.d("%s%s--onDetach", f::class.java.simpleName, "(${f.hashCode()})")
    }
}