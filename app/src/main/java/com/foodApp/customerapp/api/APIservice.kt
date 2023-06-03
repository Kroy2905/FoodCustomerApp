package com.foodApp.customerapp.api

//import com.myelin.ics.ui.models.*
import com.foodApp.customerapp.models.*
import com.foodApp.managementapp.models.demoResponse
import com.foodApp.managementapp.models.fooditemResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface APIservice {
    @GET("/prd")    // API to be written here
     suspend fun demofunc() : Response <demoResponse>

//
//
//    @POST("/addcustomerdetails")    // API to add customer
//    suspend fun  addCustomer(@Body customerDetails: customerDetails) : Response <LogResponse>
//
//    // GET trip details
//    @GET("/tripdetails")    // API to be written here
//    suspend fun getTripDetails(@Query("Veh_Reg_No") registration_no:String) : Response <tripResponse>
//
//
//    @GET("/getdriverfe")    // API to be written here
//    suspend fun getFaceEmbs(@Query("DriverID1") DriverID1:String,
//                            @Query("DriverID2") DriverID2:String,
//                            @Query("DriverID3") DriverID3:String
//    ) : Response <faceEmbeddings>
//
//    @POST("/createevent")    // API to send log details to server
//    suspend fun  createEvent(@Body logDetails: LogDetails) : Response <LogResponse>

    @POST("/customerregister")    // API to verify restaurant
    suspend fun  registerCustomer(@Body custRegBody: custRegBody) : Response <statusResponse>


    @POST("/customerverify")    // API to verify restaurant
    suspend fun  verifyCustomer(@Body custVerifyBody: custVerifyBody) : Response <statusResponse>

    // GET trip details
    @GET("/getfoods")    // API to be written here
    suspend fun getFood(@Query("restaurantID") restaurantID:String) : Response <fooditemResponse>

    // GET trip details
    @GET("/restaurant")    // API to be written here
    suspend fun getRestaurantDetails(@Query("restaurantID") restaurantID:String) : Response <restaurantItemResponseItem>

    // GET trip details
    @GET("/getrestaurants")    // API to be written here
    suspend fun getRestaurants() : Response <restaurantItemResponse>
    // GET trip details
    @GET("/getallfood")    // API to be written here
    suspend fun getAllFoodItems() : Response <allfoodItemsResponse>
}
