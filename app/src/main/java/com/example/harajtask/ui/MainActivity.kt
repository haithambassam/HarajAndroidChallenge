package com.example.harajtask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PatternMatcher
import android.util.Patterns
import androidx.activity.viewModels
import com.example.harajtask.databinding.ActivityMainBinding
import com.example.harajtask.util.setAppLanguage

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        setAppLanguage()
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar.apply { title = "" })
        setContentView(binding.root)
        viewModel.getAdvertisements()
    }


}