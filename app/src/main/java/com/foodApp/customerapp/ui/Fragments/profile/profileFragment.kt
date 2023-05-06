package com.foodApp.customerapp.ui.Fragments.profile

import com.foodApp.customerapp.base.BaseFragment

import com.foodApp.customerapp.databinding.FragmentProfileBinding



class profileFragment : BaseFragment<FragmentProfileBinding ,profileFragmentViewModel>(
   profileFragmentViewModel::class.java,
    FragmentProfileBinding::inflate
) {


    override fun setupViews() {

    }
}