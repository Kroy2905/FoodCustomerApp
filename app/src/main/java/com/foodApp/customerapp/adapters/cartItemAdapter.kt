package com.foodApp.customerapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foodApp.customerapp.R
import com.foodApp.customerapp.Utilities.OnCartItemChangeListener
import com.foodApp.customerapp.models.cartItems
import com.foodApp.managementapp.models.fooditemResponse
import java.util.*

class cartItemAdapter(private val data: List<cartItems>, context: Context, private val cartItemChangeListener: OnCartItemChangeListener) : RecyclerView.Adapter<cartItemAdapter.ViewHolder>() {

    var cartItemList = mutableListOf<cartItems>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.food_title)
        val price: TextView = itemView.findViewById(R.id.cartTotalText)

        val minustext: TextView = itemView.findViewById(R.id.minus_button)
        val countertext: TextView = itemView.findViewById(R.id.counter_text)
        val plustext: TextView = itemView.findViewById(R.id.plus_button)
    }

    // Inflate the view holder layout and return a new ViewHolder instance
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item_layout, parent, false)
        return ViewHolder(view)
    }

    // Bind the data to the views in the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        for(x in data){
            if(!cartItemList.contains(x)){
                cartItemList.add(x)
            }
        }
        val item = data[position]
        holder.title.text=item.foodTitle
        holder.countertext.text=cartItemList[position].quantity.toString()
        holder.price.text= (Integer.parseInt(item.price)*cartItemList[position].quantity).toString()

        holder.plustext.setOnClickListener {
            cartItemList[position].quantity++
            holder.countertext.text=cartItemList[position].quantity.toString()
            cartItemChangeListener.onCartItemChange(cartItemList[position])
            holder.price.text= (Integer.parseInt(cartItemList[position].price)*cartItemList[position].quantity).toString()
        }
        holder.minustext.setOnClickListener {

            if( cartItemList[position].quantity<=0){
                holder.countertext.text=cartItemList[position].quantity.toString()
                holder.price.text= (Integer.parseInt(cartItemList[position].price)*cartItemList[position].quantity).toString()


                // Exit cart logic

            }else{
                cartItemList[position].quantity--
                holder.countertext.text=cartItemList[position].quantity.toString()
                holder.price.text= (Integer.parseInt(cartItemList[position].price)*cartItemList[position].quantity).toString()

            }
            cartItemChangeListener.onCartItemChange(cartItemList[position])
        }

    }

    // Return the number of items in the data set
    override fun getItemCount(): Int {
        return data.size
    }
}
