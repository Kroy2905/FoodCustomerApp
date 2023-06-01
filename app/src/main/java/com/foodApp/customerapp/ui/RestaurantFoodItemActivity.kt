package com.foodApp.customerapp.ui

import com.foodApp.customerapp.adapters.fooditemadapter
import com.foodApp.customerapp.base.BaseActivity
import com.foodApp.customerapp.databinding.ActivityRestaurantFooditemBinding
import com.foodApp.customerapp.databinding.ActivitySearchBinding
import com.foodApp.customerapp.viewmodels.SearchActivityViewModel

class RestaurantFoodItemActivity : BaseActivity<ActivityRestaurantFooditemBinding, SearchActivityViewModel>(
   SearchActivityViewModel::class.java,
    { inflater -> ActivityRestaurantFooditemBinding.inflate(inflater) }
) {

    override fun setupViews() {


        viewModel.getfoodItems.observe(this){
            val fooditemadaper = fooditemadapter(it, this@RestaurantFoodItemActivity)
            //   binding.foodItemListRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.restaurantItemListRecyclerview.adapter = fooditemadaper
        }
        val restaurantId = intent.getStringExtra("restaurantId")
        val restaurantName = intent.getStringExtra("restaurantName")
        val restaurantAddress = intent.getStringExtra("restaurantAddress")
        binding.address.text=restaurantAddress
        binding.restaurantName.text=restaurantName
        viewModel.getFoodItems(restaurantId = restaurantId!!)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
      finish()
        super.onBackPressed()
    }

}