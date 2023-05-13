package com.foodApp.customerapp.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.foodApp.customerapp.api.APIservice
import com.foodApp.customerapp.models.custRegBody
import com.foodApp.customerapp.models.custVerifyBody
import com.foodApp.customerapp.models.restaurantItemResponse
import com.foodApp.customerapp.models.statusResponse

import com.foodApp.managementapp.models.demoResponse
import com.foodApp.managementapp.models.fooditemResponse

class BaseRepository  (private  val  apIservice: APIservice) {

    private val demoLiveData = MutableLiveData<demoResponse>()

    private val customerVerify =MutableLiveData<statusResponse>()
    private val regCustomer =MutableLiveData<statusResponse>()
    private val getfooditem =MutableLiveData<fooditemResponse>()
    private val getrestauranitem =MutableLiveData<restaurantItemResponse>()
    //    private val faceEmbeddings =MutableLiveData<faceEmbeddings>()
//    val driverlogsLogResponse =MutableLiveData<LogResponse>()
//
    val demoData: LiveData<demoResponse>
        get() = demoLiveData
    // Partner live data
    val custVerifyResponse: LiveData<statusResponse>
        get() = customerVerify
    //    Restaurant Live Data
    val custRegResponse: LiveData<statusResponse>
        get() = regCustomer
    val _getfooditem: LiveData<fooditemResponse>
        get() = getfooditem
    val _getrestaurantitem: LiveData<restaurantItemResponse>
        get() = getrestauranitem
    suspend fun getDemodata() {
        val result = apIservice.demofunc()
        if (result.body()!=null){
            demoLiveData.postValue(result.body())
        }
    }
    suspend fun registerCust(regBody: custRegBody) {
        Log.d("Body->","Req->${regBody.toString()}")
        val result = apIservice.registerCustomer(regBody)
        Log.d("Body->","Response->${result.body()}")
        if (result.body()!=null){
            regCustomer.postValue(result.body())
        }
    }
    suspend fun verifyCust(custVerifyBody: custVerifyBody) {
        val result = apIservice.verifyCustomer(custVerifyBody)
        if (result.body()!=null){
            customerVerify.postValue(result.body())
        }
    }

    suspend fun getFooditems(restaurantId:String) {
        val result = apIservice.getFood(restaurantID = restaurantId)
        if (result.body()!=null){
            Log.d("Response->",result.body().toString())
            getfooditem.postValue(result.body())
        }else{
            Log.d("Response->",result.body().toString())
        }

    }


    suspend fun getRestaurantitems() {
        val result = apIservice.getRestaurants()
        if (result.body()!=null){
            Log.d("Response->",result.body().toString())
            getrestauranitem.postValue(result.body())
        }else{
            Log.d("Response->",result.body().toString())
        }

    }




//    suspend fun getTripDetails(registration_no: String) {
//        val result = apIservice.getTripDetails(registration_no)
//        if (result.body() != null) {
//            tripdetails.postValue(result.body())
//        }
//    }
//        suspend fun getFaceEmbeddings(driverID1: String,driverID2:String,driverID3: String) {
//            val result = apIservice.getFaceEmbs(driverID1,driverID2,driverID3)
//            if (result.body()!=null){
//                faceEmbeddings.postValue(result.body())
//            }
//
//    }
//    suspend fun sendDriverLogs(logDetails: LogDetails): Response<LogResponse> {
//        val result = apIservice.createEvent(logDetails)
//        if (result.body()!=null){
//            driverlogsLogResponse.postValue(result.body())
//        }
//        return  result
//    }


}