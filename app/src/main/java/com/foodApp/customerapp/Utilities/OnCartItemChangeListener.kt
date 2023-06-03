package com.foodApp.customerapp.Utilities

import com.foodApp.customerapp.models.cartItems

interface OnCartItemChangeListener {
    fun onCartItemChange(cartItem: cartItems)
}
