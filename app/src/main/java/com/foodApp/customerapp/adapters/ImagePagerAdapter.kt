package com.foodApp.customerapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.foodApp.customerapp.R
import com.foodApp.customerapp.models.CategoryDomain
import com.foodApp.customerapp.ui.Fragments.ImageFragment

class ImagePagerAdapter(fm: FragmentManager, private val imageIds: List<Int>) : FragmentPagerAdapter(fm) {

   // private val imageIds = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)

    override fun getItem(position: Int): Fragment {
        return ImageFragment.newInstance(imageIds[position])
    }

    override fun getCount(): Int {
        return imageIds.size
    }

}
