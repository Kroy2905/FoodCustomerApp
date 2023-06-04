package com.foodApp.customerapp.models

import android.os.Parcel
import android.os.Parcelable

data class cartItems(
    val foodId: String,
    val foodTitle: String,
    val restaurantId: String,
    val price: String,
    var quantity: Int
) : Parcelable {
    // TODO: Implement Parcelable methods here

    // Parcelable creator
    companion object CREATOR : Parcelable.Creator<cartItems> {
        override fun createFromParcel(parcel: Parcel): cartItems {
            return cartItems(parcel)
        }

        override fun newArray(size: Int): Array<cartItems?> {
            return arrayOfNulls(size)
        }
    }

    // Constructor for parcelable
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()
    )

    // Write object's data to the parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(foodId)
        parcel.writeString(foodTitle)
        parcel.writeString(restaurantId)
        parcel.writeString(price)
        parcel.writeInt(quantity)
    }

    // Describe the kind of special object contained in this Parcelable instance
    override fun describeContents(): Int {
        return 0
    }
}
