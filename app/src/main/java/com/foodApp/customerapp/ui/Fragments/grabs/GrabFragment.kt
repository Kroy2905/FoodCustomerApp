package com.foodApp.customerapp.ui.Fragments.grabs

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.foodApp.customerapp.R
import com.foodApp.customerapp.base.BaseFragment
import com.foodApp.customerapp.databinding.FragmentGrabBinding
import com.foodApp.customerapp.databinding.FragmentHomeBinding

class GrabFragment : BaseFragment<FragmentGrabBinding, GrabViewModel>(
GrabViewModel::class.java,
FragmentGrabBinding::inflate
) {


    override fun setupViews() {

    }

}