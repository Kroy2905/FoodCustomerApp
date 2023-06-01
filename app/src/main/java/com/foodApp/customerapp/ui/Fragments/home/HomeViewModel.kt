package com.foodApp.customerapp.ui.Fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodApp.customerapp.base.BaseRepository
import com.foodApp.customerapp.base.BaseViewModel
import com.foodApp.customerapp.models.restaurantItemResponse
import com.foodApp.managementapp.models.fooditemResponse
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: BaseRepository): BaseViewModel(){

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


    fun getFoodItems(restaurantId:String){
        viewModelScope.launch {
            repository.getFooditems(restaurantId)
        }
    }
    val  getfoodItems : LiveData<fooditemResponse>
        get() = repository._getfooditem

    fun getRestaurantItems(){
        viewModelScope.launch {
            repository.getRestaurantitems()
        }
    }
    val  getRestaurantItems : LiveData<restaurantItemResponse>
        get() = repository._getrestaurantitem

}