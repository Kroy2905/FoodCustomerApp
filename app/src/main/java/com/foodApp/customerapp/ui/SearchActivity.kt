package com.foodApp.customerapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.foodApp.customerapp.adapters.fooditemadapter
import com.foodApp.customerapp.base.BaseActivity
import com.foodApp.customerapp.databinding.ActivitySearchBinding
import com.foodApp.customerapp.databinding.ActivitySplashScreenBinding
import com.foodApp.customerapp.viewmodels.MainViewModel
import com.foodApp.customerapp.viewmodels.SearchActivityViewModel

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchActivityViewModel>(
   SearchActivityViewModel::class.java,
    { inflater -> ActivitySearchBinding.inflate(inflater) }
) {

    override fun setupViews() {


        viewModel.getfoodItems.observe(this){
            val fooditemadaper = fooditemadapter(it, this@SearchActivity)
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