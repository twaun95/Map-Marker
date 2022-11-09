package com.twaun95.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.twaun95.presentation.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}