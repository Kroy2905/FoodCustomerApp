package com.foodApp.customerapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.foodApp.customerapp.R
import com.foodApp.customerapp.models.CategoryDomain

class CategoryAdapter(private val data: List<CategoryDomain>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    // Create a ViewHolder class that holds references to the views
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.categoryimage)
        val textview: TextView = itemView.findViewById(R.id.categoryname)
    }

    // Inflate the view holder layout and return a new ViewHolder instance
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item_view, parent, false)
        return ViewHolder(view)
    }

    // Bind the data to the views in the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.imageView.setImageResource(item.pic)
        holder.textview.text=item.title
    }

    // Return the number of items in the data set
    override fun getItemCount(): Int {
        return data.size
    }
}
