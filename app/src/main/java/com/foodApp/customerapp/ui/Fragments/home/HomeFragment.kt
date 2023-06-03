package com.foodApp.customerapp.ui.Fragments.home
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.foodApp.customerapp.R
import com.foodApp.customerapp.Utilities.OnItemClickListener
import com.foodApp.customerapp.Utilities.searchItemInterface
import com.foodApp.customerapp.adapters.CategoryAdapter
import com.foodApp.customerapp.adapters.ImagePagerAdapter
import com.foodApp.customerapp.adapters.Restaurantitemadapter
import com.foodApp.customerapp.base.BaseFragment
import com.foodApp.customerapp.databinding.FragmentHomeBinding
import com.foodApp.customerapp.models.CategoryDomain
import com.foodApp.customerapp.models.searchItems
import com.foodApp.customerapp.ui.RestaurantFoodItemActivity
import com.foodApp.customerapp.ui.SearchActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>(
   HomeViewModel::class.java,
    FragmentHomeBinding::inflate
) , ViewPager.OnPageChangeListener {
    private lateinit var imagePagerAdapter: ImagePagerAdapter
    private lateinit var timer: Timer

    private var handler = Handler()
    private var autoSliderRunnable: Runnable? = null
    private var mListener: searchItemInterface? = null

    private val imageIds = listOf(R.drawable.discount1,
        R.drawable.discount2,
        R.drawable.discount3,
        R.drawable.discount4,
        R.drawable.discount5,
        R.drawable.discount6,
    )
    val coroutineScope = CoroutineScope(Dispatchers.Main)
    private fun startAutoImageSlider() {
        coroutineScope.launch {
            stopAutoImageSlider() // Cancel any existing Runnable
            //AutoImageSlider code


            imagePagerAdapter = ImagePagerAdapter(requireActivity().supportFragmentManager,imageIds)
            binding.sliderViewPager.adapter = imagePagerAdapter
            binding.sliderViewPager.addOnPageChangeListener(this@HomeFragment)





            autoSliderRunnable = object : Runnable {
                override fun run() {
                    binding.sliderViewPager.post {
                        binding.sliderViewPager.currentItem = (binding.sliderViewPager.currentItem + 1) % imagePagerAdapter.count
                    }
                    handler.postDelayed(this, 5000)
                }
            }

            handler.postDelayed(autoSliderRunnable!!, 5000)
        }

    }
    private fun stopAutoImageSlider() {
        autoSliderRunnable?.let {
            handler.removeCallbacks(it)
            autoSliderRunnable = null
        }
    }


    var restaurantList = mutableListOf<searchItems>()
    @SuppressLint("SetTextI18n")
    override fun setupViews() {
        restaurantList.clear()
        //Top categories
        binding.searchBox.setOnClickListener {
            val intent = Intent(requireContext(), SearchActivity::class.java)
            intent.putParcelableArrayListExtra("list", ArrayList(restaurantList))
            intent.putExtra("category", "")
            startActivity(intent)
        }

        val data = mutableListOf<CategoryDomain>()
        data.add(CategoryDomain("Pizza", R.drawable.pizza))
        data.add(CategoryDomain("Burger", R.drawable.burger))
        data.add(CategoryDomain("Biryani", R.drawable.biriyani))
        data.add(CategoryDomain("Noodles", R.drawable.noodles))
        data.add(CategoryDomain("Dosa", R.drawable.idlidhosa))
        data.add(CategoryDomain("Thali", R.drawable.thali))
        val adapter = CategoryAdapter(data)
        binding.topcategoryRecyclerview.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.topcategoryRecyclerview.adapter = adapter
        adapter.setOnItemClickListener(object:CategoryAdapter.OnItemClickListener{
            override fun onItemClick(item: CategoryDomain) {

                val intent = Intent(requireContext(), SearchActivity::class.java)
                intent.putExtra("category", item.title)
                intent.putParcelableArrayListExtra("list", ArrayList(restaurantList))
                startActivity(intent)

             //   Toast.makeText(requireContext(), "Clicked on ${item.title}", Toast.LENGTH_SHORT).show()
            }

        })

        //All restaurants

        viewModel.getRestaurantItems.observe(this){

            for(x in it){

                if(!restaurantList.contains(searchItems(x.restaurantName,
                        x.RestaurantImgUrl,1,
                        x.restaurantID)
                    )){
                    restaurantList.add(
                        searchItems(x.restaurantName,
                            x.RestaurantImgUrl,1,
                            x.restaurantID)
                    )
                }

            }
            binding.chooseText.text= "${it.size} restaurants at your service"
            binding.recyclerview2.adapter=
                Restaurantitemadapter(it,object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val intent = Intent(requireContext(), RestaurantFoodItemActivity::class.java)
                    intent.putExtra("restaurantId", it[position].restaurantID)
                    intent.putExtra("restaurantName", it[position].restaurantName)
                    intent.putExtra("restaurantAddress", it[position].restaurantAddress)
                    startActivity(intent)
                    //  Toast.makeText(requireContext(),it[position].restaurantName,Toast.LENGTH_SHORT).show()
                }

                })
        }
        mListener?.onListReceived(restaurantList)
        viewModel.getRestaurantItems()

//                //Best offers kept same as above
//        binding.recyclerview3.layoutManager = GridLayoutManager(requireContext(), 2)
//        binding.recyclerview3.adapter = adapter2







    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startAutoImageSlider()


        super.onViewCreated(view, savedInstanceState)
    }





    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onResume() {
        super.onResume()
       startAutoImageSlider()
    }

    override fun onPause() {
      //  stopAutoImageSlider() // Cancel any existing Runnable

        super.onPause()

    }

    override fun onDestroy() {
        stopAutoImageSlider() // Cancel any existing Runnable

        super.onDestroy()
    }
}