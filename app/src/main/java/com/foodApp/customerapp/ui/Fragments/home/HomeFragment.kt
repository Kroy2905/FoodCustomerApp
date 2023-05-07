package com.foodApp.customerapp.ui.Fragments.home
import android.os.Bundle
import android.os.Handler
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.foodApp.customerapp.R
import com.foodApp.customerapp.adapters.CategoryAdapter
import com.foodApp.customerapp.adapters.FastDeliveryAdapter
import com.foodApp.customerapp.adapters.ImagePagerAdapter
import com.foodApp.customerapp.base.BaseFragment
import com.foodApp.customerapp.databinding.FragmentHomeBinding
import com.foodApp.customerapp.models.CategoryDomain
import com.foodApp.customerapp.models.FastDeliveryDomain
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>(
   HomeViewModel::class.java,
    FragmentHomeBinding::inflate
) , ViewPager.OnPageChangeListener {
    private lateinit var imagePagerAdapter: ImagePagerAdapter
    private lateinit var timer: Timer
    private val handler = Handler()

    override fun setupViews() {

        val data = mutableListOf<CategoryDomain>()
        data.add(CategoryDomain("Pizza", R.drawable.pizza))
        data.add(CategoryDomain("Burger", R.drawable.burger))
        data.add(CategoryDomain("Biryani", R.drawable.biriyani))
        data.add(CategoryDomain("Noodles", R.drawable.noodles))
        val adapter = CategoryAdapter(data)
        binding.recyclerview1.adapter = adapter


        //AutoImageSlider code

        val imageIds = listOf(R.drawable.discount1,
            R.drawable.discount2,
            R.drawable.discount3,
            R.drawable.discount4,
            R.drawable.discount5,
            R.drawable.discount6,
        )
       imagePagerAdapter = ImagePagerAdapter(requireActivity().supportFragmentManager,imageIds)
        binding.sliderViewPager.adapter = imagePagerAdapter
        binding.sliderViewPager.addOnPageChangeListener(this)



        // Start auto-scrolling after a delay of 2 seconds
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post {
                   binding.sliderViewPager.currentItem = (binding.sliderViewPager.currentItem + 1) % imagePagerAdapter.count
                }
            }
        }, 3000, 5000)



        val data2 = mutableListOf<FastDeliveryDomain>()
        data2.add(FastDeliveryDomain("Pizza Hut", R.drawable.pizzafullimg,"4.2 (30k+ reviews)",32))
        data2.add(
            FastDeliveryDomain("Dada Bowdi",
                R.drawable.biryanifullimg,"4.7 (10k+ reviews)",52)
        )
        data2.add(FastDeliveryDomain("Chowman", R.drawable.noodlesfullimg,"4.0 (3000+ reviews)",22))
        data2.add(
            FastDeliveryDomain("Burger King",
                R.drawable.burgerfullimg,"4.2 (300+ reviews)",45)
        )
        val adapter2 = FastDeliveryAdapter(data2)
        binding.recyclerview2.adapter = adapter2



    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }
}