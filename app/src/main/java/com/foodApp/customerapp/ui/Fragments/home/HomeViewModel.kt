package com.foodApp.customerapp.ui.Fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foodApp.customerapp.base.BaseRepository
import com.foodApp.customerapp.base.BaseViewModel

class HomeViewModel(private val repository: BaseRepository): BaseViewModel(){

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}