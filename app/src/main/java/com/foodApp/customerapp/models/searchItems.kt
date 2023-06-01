package com.foodApp.customerapp.models

import android.os.Parcel
import android.os.Parcelable

data class searchItems(   val name:String,
                          val imageUrl:String,
                          val type:Int,   //1 is for restaurant 2 is for Dish
                          val id:String

): Parcelable {

    constructor(parcel: Parcel) :this (
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(imageUrl)
        parcel.writeInt(type)
        parcel.writeString(id)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<searchItems> {
        override fun createFromParcel(parcel: Parcel): searchItems {
            return searchItems(parcel)
        }

        override fun newArray(size: Int): Array<searchItems?> {
            return arrayOfNulls(size)
        }
    }
}





