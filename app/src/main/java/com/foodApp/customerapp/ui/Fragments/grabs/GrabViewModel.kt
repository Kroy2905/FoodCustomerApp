package com.foodApp.customerapp.ui.Fragments.grabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodApp.customerapp.base.BaseRepository
import com.foodApp.customerapp.base.BaseViewModel
import com.foodApp.customerapp.models.restaurantItemResponse
import com.foodApp.managementapp.models.fooditemResponse
import kotlinx.coroutines.launch

class GrabViewModel (private val repository: BaseRepository): BaseViewModel() {


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