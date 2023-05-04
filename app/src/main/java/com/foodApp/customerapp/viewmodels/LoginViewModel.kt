package com.foodApp.customerapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.foodApp.customerapp.base.BaseRepository
import com.foodApp.customerapp.base.BaseViewModel
import com.foodApp.customerapp.models.custRegBody
import com.foodApp.customerapp.models.custVerifyBody
import com.foodApp.customerapp.models.statusResponse
import kotlinx.coroutines.launch


class LoginViewModel(private val repository: BaseRepository): BaseViewModel(){
    fun verifyCustomer(custVerifyBody: custVerifyBody){
        viewModelScope.launch {
            repository.verifyCust(custVerifyBody)

        }
    }
    val  custVerifyVar : LiveData<statusResponse>
        get() = repository.custVerifyResponse

}