package com.foodApp.customerapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.foodApp.customerapp.base.BaseRepository
import com.foodApp.customerapp.base.BaseViewModel
import com.foodApp.customerapp.models.allfoodItemsResponse
import com.foodApp.customerapp.models.restaurantItemResponse
import com.foodApp.managementapp.models.demoResponse
import kotlinx.coroutines.launch

class SearchViewmodel (private val repository: BaseRepository): BaseViewModel(){

    init {
        viewModelScope.launch {
            repository.getDemodata()

        }

    }
    val  demoData : LiveData<demoResponse>
        get() = repository.demoData

    fun getallFoodItems(){
        viewModelScope.launch {
            repository.getAllFoodItems()
        }
    }
    val  allFooditems : LiveData<allfoodItemsResponse>
        get() = repository._allfooditems

}