package com.foodApp.customerapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.foodApp.customerapp.base.BaseRepository
import com.foodApp.customerapp.base.BaseViewModel
import com.foodApp.customerapp.models.custRegBody
import com.foodApp.customerapp.models.statusResponse
import kotlinx.coroutines.launch


class RegistrationViewModel(private val repository: BaseRepository): BaseViewModel(){



    fun registerCustomer(regBody: custRegBody){
        viewModelScope.launch {
            repository.registerCust(regBody)

        }
    }
    val  registrationVar : LiveData<statusResponse>
        get() = repository.custRegResponse

}