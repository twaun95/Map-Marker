package com.twaun95.presentation.ui.webview

import android.content.Context
import android.content.Intent
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
        intent.getStringExtra(URL)?.let {
            binding.viewWeb.loadUrl(it)
        }
    }

    override fun setEvent() {
        super.setEvent()
    }

    override fun setObserver() {
        super.setObserver()
    }

    companion object {
        const val URL = "URL"
        fun newInstance(context: Context, url: String) {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra(URL, url)
            context.startActivity(intent)
        }
    }
}