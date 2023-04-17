package com.twaun95.presentation.ui.webview

import androidx.activity.viewModels
import com.twaun95.presentation.R
import com.twaun95.presentation.base.BaseActivity
import com.twaun95.presentation.databinding.ActivityWebviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewActivity : BaseActivity<ActivityWebviewBinding>(R.layout.activity_webview){
    private val viewModel by viewModels<WebViewActivityViewModel>()

    override fun initView() {
        super.initView()
    }

    override fun setEvent() {
        super.setEvent()
    }

    override fun setObserver() {
        super.setObserver()
    }

    companion object {

        fun newInstance() : WebViewActivity = WebViewActivity()
    }
}