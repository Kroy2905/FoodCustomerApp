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

class fooditemadapter(private val data: fooditemResponse,context: Context, private val cartItemChangeListener: OnCartItemChangeListener) : RecyclerView.Adapter<fooditemadapter.ViewHolder>() {

    var cartItemList = mutableListOf<cartItems>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.food_image)
        val title: TextView = itemView.findViewById(R.id.food_title)
        val price: TextView = itemView.findViewById(R.id.food_price)
        val ratiing: TextView = itemView.findViewById(R.id.food_rating)
        val addtextview: TextView = itemView.findViewById(R.id.addtext)
        val minustext: TextView = itemView.findViewById(R.id.minustext)
        val countertext: TextView = itemView.findViewById(R.id.counterText)
        val plustext: TextView = itemView.findViewById(R.id.plustext)
    }

    // Inflate the view holder layout and return a new ViewHolder instance
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fooditemlayout, parent, false)
        return ViewHolder(view)
    }

    // Bind the data to the views in the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        for(x in data){

            val x = cartItems(

                foodId = x.foodID,
                foodTitle = x.foodTitle,
                restaurantId = x.restaurantID,
                price = x.foodPrice.toString(),
                0

            )
            if(!cartItemList.contains(x)){
                cartItemList.add(x)
            }
        }
        val item = data[position]


        Glide.with(holder.itemView.context/* context */)
            //.load(storageRef.child("/fooditems/foodItem1.jpeg"))
            .load(item.foodImgUrl)
            .error(R.drawable.app_logo)
            .into(holder.imageView)


        holder.title.text=item.foodTitle
        val random = Random()
        val randomNumber = (1.0f + random.nextFloat() * 3.9f).toString()
        holder.ratiing.text = randomNumber.substring(0, minOf(randomNumber.length, 3))
        holder.price.text= item.foodPrice.toString()

        holder.addtextview.setOnClickListener {
            holder.addtextview.visibility=View.GONE
            holder.plustext.visibility=View.VISIBLE
            holder.countertext.visibility=View.VISIBLE
            holder.minustext.visibility=View.VISIBLE
            cartItemList[position].quantity++
            holder.countertext.text=cartItemList[position].quantity.toString()
            cartItemChangeListener.onCartItemChange(cartItemList[position])
        }
        holder.plustext.setOnClickListener {
            cartItemList[position].quantity++
            holder.countertext.text=cartItemList[position].quantity.toString()
            cartItemChangeListener.onCartItemChange(cartItemList[position])
        }
        holder.minustext.setOnClickListener {
            cartItemList[position].quantity--
            if( cartItemList[position].quantity==0){
                holder.addtextview.visibility=View.VISIBLE
                holder.plustext.visibility=View.GONE
                holder.countertext.visibility=View.GONE
                holder.minustext.visibility=View.GONE
            }else{
                holder.countertext.text=cartItemList[position].quantity.toString()
            }
            cartItemChangeListener.onCartItemChange(cartItemList[position])
        }

    }

    // Return the number of items in the data set
    override fun getItemCount(): Int {
        return data.size
    }
}
