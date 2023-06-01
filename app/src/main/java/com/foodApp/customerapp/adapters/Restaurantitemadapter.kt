package com.foodApp.customerapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foodApp.customerapp.R
import com.foodApp.customerapp.Utilities.OnItemClickListener
import com.foodApp.customerapp.models.restaurantItemResponse
import com.foodApp.customerapp.models.restaurantItemResponseItem
import java.util.*

class Restaurantitemadapter(private val data: restaurantItemResponse, private val listener:OnItemClickListener) : RecyclerView.Adapter<Restaurantitemadapter.ViewHolder>() {

    // Create a ViewHolder class that holds references to the views
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: restaurantItemResponseItem, listener: OnItemClickListener) {
            itemView.setOnClickListener { listener.onItemClick(adapterPosition) }
        }

        val imageView: ImageView = itemView.findViewById(R.id.searchitem_image)
        val title: TextView = itemView.findViewById(R.id.searchitem_title)
        val address: TextView = itemView.findViewById(R.id.searchitem_type)
        val rating: TextView = itemView.findViewById(R.id.restaurant_rating)
    }

    // Inflate the view holder layout and return a new ViewHolder instance
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurantitemlayout, parent, false)
        return ViewHolder(view)
    }

    // Bind the data to the views in the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], listener)
        val item = data[position]


        Glide.with(holder.itemView.context/* context */)
            //.load(storageRef.child("/fooditems/foodItem1.jpeg"))
            .load(item.RestaurantImgUrl)
            .error(R.drawable.app_logo)
            .into(holder.imageView)



        holder.title.text=item.restaurantName
        val random = Random()
        val randomNumber = (1.0f + random.nextFloat() * 3.9f).toString()
        holder.rating.text = randomNumber.substring(0, minOf(randomNumber.length, 3))
        holder.address.text= item.restaurantAddress.toString()
    }

    // Return the number of items in the data set
    override fun getItemCount(): Int {
        return data.size
    }
}
