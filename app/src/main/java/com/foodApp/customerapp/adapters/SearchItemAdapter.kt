package com.foodApp.customerapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foodApp.customerapp.R
import com.foodApp.customerapp.Utilities.OnItemClickListener
import com.foodApp.customerapp.models.restaurantItemResponseItem
import com.foodApp.customerapp.models.searchItems
import java.util.*


class SearchItemAdapter(private val itemList: List<searchItems>) : RecyclerView.Adapter<SearchItemAdapter.ViewHolder>(), Filterable {
    private var filteredList: List<searchItems> = itemList
    private var itemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = filteredList[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.trim().orEmpty()

                val filteredItems = if (query.isEmpty()) {
                    itemList
                } else {
                    itemList.filter { item ->
                        item.name.contains(query, ignoreCase = true)
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredItems
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as? List<searchItems> ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val nameTextView: TextView = itemView.findViewById(R.id.searchitem_title)
         val type: TextView = itemView.findViewById(R.id.searchitem_type)
        val image: ImageView = itemView.findViewById(R.id.searchitem_image)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = filteredList[position]
                    itemClickListener?.onItemClick(item)
                }
            }
        }

        fun bind(item: searchItems) {
            nameTextView.text = item.name
            if(item.type==1){
                type.text="Restaurant"
            }else{
                type.text="Food"
            }

            Glide.with(itemView.context)
                //.load(storageRef.child("/fooditems/foodItem1.jpeg"))
                .load(item.imageUrl)
                .error(R.drawable.app_logo)
                .into(image)

        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: searchItems)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }
}
