package ru.ds.weather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import ru.ds.weather.view.main.HistoryFragment
import ru.ds.weather.R
import ru.ds.weather.databinding.MainActivityBinding
import ru.ds.weather.experiment.ContactFragment
import ru.ds.weather.view.maps.GoogleMapsFragment

import ru.ds.weather.view.main.MainFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
         showMainFragment()
             //showFragment(ThreadsFragment.newInstance())
        }

    }

    private fun showFragment(f: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, f)
            .commitNow()
    }
    private fun showFragmentWithBackStack(f: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, f)
            .addToBackStack(null)
            .commit()
    }

    private fun showMainFragment(){
        showFragment(MainFragment.newInstance())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                showFragmentWithBackStack(HistoryFragment.newInstance())
                true
            }
            R.id.menu_contacts ->{
                showFragmentWithBackStack(ContactFragment.newInstance())
                true
            }
            R.id.menu_google_maps ->{
                showFragmentWithBackStack(GoogleMapsFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }
}