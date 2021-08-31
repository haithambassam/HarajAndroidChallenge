package com.example.harajtask.ui

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.app.Application
import androidx.lifecycle.*
import com.example.harajtask.model.Ad


class MainViewModel(val context: Application) : AndroidViewModel(context) {

    private val _ads = MutableLiveData<ArrayList<Ad>>()
    val ads: LiveData<ArrayList<Ad>> = _ads

    private val _selectedAd = MutableLiveData<Ad>()
    val selectedAd: LiveData<Ad> = _selectedAd

    fun getAdvertisements() {
        // Get list from Json
        val json: String = context.resources.assets.open("data.json").bufferedReader().use { it.readText() }
        val groupListType = object : TypeToken<ArrayList<Ad>>() {}.type
        val ads: ArrayList<Ad> = Gson().fromJson(json, groupListType)

        _ads.value = ads
    }


    fun selectAd(position: Int) {
        ads.value?.get(position)?.let {
            _selectedAd.value = it
        }
    }
}