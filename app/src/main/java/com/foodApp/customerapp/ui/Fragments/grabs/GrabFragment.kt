package com.foodApp.customerapp.ui.Fragments.grabs

import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import com.foodApp.customerapp.R
import com.foodApp.customerapp.Utilities.OnItemClickListener
import com.foodApp.customerapp.adapters.CategoryAdapter
import com.foodApp.customerapp.adapters.FastDeliveryAdapter
import com.foodApp.customerapp.adapters.Restaurantitemadapter
import com.foodApp.customerapp.base.BaseFragment
import com.foodApp.customerapp.databinding.FragmentGrabBinding
import com.foodApp.customerapp.models.TopRestaurantDomain
import com.foodApp.customerapp.ui.RestaurantFoodItemActivity

class GrabFragment : BaseFragment<FragmentGrabBinding, GrabViewModel>(
    GrabViewModel::class.java,
    FragmentGrabBinding::inflate
) {
    val fastdeliveryData = mutableListOf<TopRestaurantDomain>()

    override fun setupViews() {
        addDummyData()
        val adapter = FastDeliveryAdapter(fastdeliveryData)
        binding.bestofferRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.bestofferRecyclerview.adapter = adapter


        binding.nearbyrestaurantRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.nearbyrestaurantRecyclerview.adapter = adapter


        binding.whatsnewRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.whatsnewRecyclerview.adapter = adapter





    }

    fun addDummyData(){
        fastdeliveryData.add(
            TopRestaurantDomain(
                "Pizza Hut",
                R.drawable.pizzafullimg,
                "3.3",
                23
            )
        )
        fastdeliveryData.add(
            TopRestaurantDomain(
                "Arsalan",
                R.drawable.biryanifullimg,
                "3.5",
                21
            )
        )
        fastdeliveryData.add(
            TopRestaurantDomain(
                "Burger King",
                R.drawable.burgerfullimg,
                "4.0",
                18
            )
        )
        fastdeliveryData.add(
            TopRestaurantDomain(
                "Chowman",
                R.drawable.noodlesfullimg,
                "3.2",
                20
            )
        )


    }

}