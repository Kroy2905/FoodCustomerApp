package com.foodApp.customerapp.Utilities

import com.foodApp.customerapp.models.searchItems

interface searchItemInterface {
    fun onListReceived(myList: List<searchItems>)
}