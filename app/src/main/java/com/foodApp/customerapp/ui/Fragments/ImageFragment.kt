package com.foodApp.customerapp.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.foodApp.customerapp.R
import com.foodApp.customerapp.base.BaseFragment
import com.foodApp.customerapp.databinding.FragmentHomeBinding
import com.foodApp.customerapp.databinding.FragmentImageBinding
import com.foodApp.customerapp.ui.Fragments.home.HomeViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageFragment : BaseFragment<FragmentImageBinding, HomeViewModel>(
    HomeViewModel::class.java,
    FragmentImageBinding::inflate
){

    private var imageId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageId = it.getInt(ARG_IMAGE_ID)
        }
    }

    companion object {

        private const val ARG_IMAGE_ID = "imageId"

        fun newInstance(imageId: Int): ImageFragment {
            val fragment = ImageFragment()
            val args = Bundle()
            args.putInt(ARG_IMAGE_ID, imageId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun setupViews() {
        binding.foodImage.setImageResource(imageId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageId = arguments?.getInt(ARG_IMAGE_ID, R.drawable.discount1)

        // Use Glide library to load the image or set the default placeholder image
        Glide.with(this)
            .load(imageId)
            .placeholder(R.drawable.discount1)
            .into(binding.foodImage)



    }


}
