package com.example.harajtask.util

import androidx.appcompat.app.AppCompatActivity
import java.util.*

 fun AppCompatActivity.setAppLanguage() {
    val configuration = resources.configuration
    configuration.setLayoutDirection(Locale("ar"))
    createConfigurationContext(configuration)
}