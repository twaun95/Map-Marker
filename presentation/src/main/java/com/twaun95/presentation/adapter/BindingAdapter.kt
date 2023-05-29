package com.twaun95.presentation.adapter

import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.twaun95.presentation.ui.search.SearchFragmentViewModel

@BindingAdapter("android:visibility")
fun setVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("android:onKeyBoardSearch")
fun setOnKeyBoardSearch(view: EditText, fragmentVM: SearchFragmentViewModel) {
    view.setOnEditorActionListener { v, actionId, event ->
        when (actionId) {
            EditorInfo.IME_ACTION_SEARCH -> {
                fragmentVM.onSearch()
                view.clearFocus()
                // 키보드 내리기
            }
            else -> {}
        }
        true
    }
}
