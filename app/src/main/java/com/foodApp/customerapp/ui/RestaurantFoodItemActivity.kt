package com.foodApp.customerapp.ui

import android.content.Intent
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.foodApp.customerapp.R
import com.foodApp.customerapp.Utilities.OnCartItemChangeListener
import com.foodApp.customerapp.adapters.fooditemadapter
import com.foodApp.customerapp.base.BaseActivity
import com.foodApp.customerapp.databinding.ActivityRestaurantFooditemBinding
import com.foodApp.customerapp.databinding.ActivitySearchBinding
import com.foodApp.customerapp.models.cartItems
import com.foodApp.customerapp.viewmodels.SearchActivityViewModel
import java.util.ArrayList

class RestaurantFoodItemActivity : BaseActivity<ActivityRestaurantFooditemBinding, SearchActivityViewModel>(
    SearchActivityViewModel::class.java,
    { inflater -> ActivityRestaurantFooditemBinding.inflate(inflater) }
) , OnCartItemChangeListener {
    var cartItemsList = mutableListOf<cartItems>()

    override fun setupViews() {

        binding.checkoutButton.setOnClickListener {

            val intent = Intent(this, BillingActivity::class.java)
            intent.putParcelableArrayListExtra("cartlist", ArrayList(cartItemsList))
            startActivity(intent)
        }


        viewModel.getfoodItems.observe(this) {
            val fooditemadaper = fooditemadapter(it, this@RestaurantFoodItemActivity,this)
            //   binding.foodItemListRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.restaurantItemListRecyclerview.adapter = fooditemadaper
        }

        viewModel.restaurantDetails.observe(this) {
            binding.restaurantName.text = it.restaurantName
            binding.address.text = it.restaurantAddress
            Glide.with(this)
                //.load(storageRef.child("/fooditems/foodItem1.jpeg"))
                .load(it.RestaurantImgUrl)
                .error(R.drawable.app_logo)
                .into(binding.custprofilepic)

        }
        val restaurantId = intent.getStringExtra("restaurantId")

        viewModel.getFoodItems(restaurantId = restaurantId!!)
        viewModel.getRestaurantDetails(restaurantId = restaurantId!!)

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
    fun getTotalitemCount(): Int{
         var totalCartitems=0
        for(x in cartItemsList){
            totalCartitems+=x.quantity
        }
        return totalCartitems
    }

    override fun onCartItemChange(cartItem: cartItems) {


        val index = cartItemsList.indexOf(cartItem)
        if (index != -1) {



            // If the cartItem already exists in the list, replace it with the received cartItem
            if(cartItemsList[index].quantity==0)
                cartItemsList.removeAt(index)
            else
                cartItemsList[index] = cartItem

           // binding.cartitemCount.text=totalCartitems.toString()


        } else {
            // If the cartItem doesn't exist in the list, add it
            cartItemsList.add(cartItem)

          //  binding.cartitemCount.text=totalCartitems.toString()
        }
        val totalitems = getTotalitemCount()

        if(totalitems<=0){
            binding.checkoutbox.visibility=View.INVISIBLE
        }else{
            binding.checkoutbox.visibility=View.VISIBLE
            binding.cartitemCount.text=totalitems.toString()
        }

    }
}