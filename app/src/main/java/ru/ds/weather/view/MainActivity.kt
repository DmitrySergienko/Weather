package ru.ds.weather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.ds.weather.R
import ru.ds.weather.databinding.MainActivityBinding
import ru.ds.weather.view.main.MainFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }

    }
}