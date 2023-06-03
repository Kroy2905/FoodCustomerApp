package com.foodApp.customerapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.foodApp.customerapp.base.BaseRepository
import com.foodApp.customerapp.base.BaseViewModel
import com.foodApp.customerapp.models.restaurantItemResponseItem
import com.foodApp.managementapp.models.demoResponse
import com.foodApp.managementapp.models.fooditemResponse
import kotlinx.coroutines.launch

class SearchActivityViewModel (private val repository: BaseRepository): BaseViewModel(){

    init {
        viewModelScope.launch {
            repository.getDemodata()

        }

    }
    val  demoData : LiveData<demoResponse>
        get() = repository.demoData


    fun getFoodItems(restaurantId:String){
        viewModelScope.launch {
            repository.getFooditems(restaurantId)
        }
    }
    val  getfoodItems : LiveData<fooditemResponse>
        get() = repository._getfooditem


    fun getRestaurantDetails(restaurantId:String){
        viewModelScope.launch {
            repository.getRestaurantDetails(restaurantId)
        }
    }
    val  restaurantDetails : LiveData<restaurantItemResponseItem>
        get() = repository._restaurantDetails


}