package ru.ds.weather.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ds.weather.R
import ru.ds.weather.databinding.ActivityBottomNavigationBinding
import ru.ds.weather.view.maps.GoogleMapsFragment

class BottomNavigationActivity : AppCompatActivity() {

    lateinit var binding: ActivityBottomNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation() //Bottom Navigation Item Selector
    }

    private fun initBottomNavigation() {
        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.menu_list_of_city)  //create badge
        badge?.number = 1
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_list_of_city-> {
                    supportFragmentManager.beginTransaction().replace(R.id.containerBottomNavigation, MainFragment()).commit()
                    binding.bottomNavigationView.removeBadge(R.id.menu_list_of_city)  //remove badge when on click
                    true
                }
                R.id.menu_history -> {
                    supportFragmentManager.beginTransaction().replace(R.id.containerBottomNavigation, HistoryFragment()).commit()
                    binding.bottomNavigationView.removeBadge(R.id.menu_history) //remove badge when on click
                    true
                }
                R.id.menu_google_maps -> {
                    supportFragmentManager.beginTransaction().replace(R.id.containerBottomNavigation, GoogleMapsFragment()).commit()
                    binding.bottomNavigationView.removeBadge(R.id.menu_google_maps) //remove badge when on click
                    true
                }
                else -> true
            }
        }
        //default view
        binding.bottomNavigationView.selectedItemId = R.id.menu_list_of_city


    }
}
