package ru.ds.weather.view
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import ru.ds.weather.viewmodel.AppState
import ru.ds.weather.R
import ru.ds.weather.databinding.MainFragmentBinding
import ru.ds.weather.model.Weather
import ru.ds.weather.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }


    //эти танцы с бубнами _binding/binding для тогоч тобы в конечном счете binding точно не был null
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val observer = Observer<AppState> { state ->
            renderData(state)
        }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
        viewModel.getWeather()
    }



    //принимаем объект сотояния приложения и через when показываем что нужно отразить. тут observer хранит данные
    private fun renderData(state: AppState) {
        when (state) {
            is AppState.Success -> {
                val weatherData = state.weatherData
                binding.loadingLayout.visibility = View.GONE
                binding.mainView.isVisible = true
                binding.cityName.text = state.weatherData.feelsLike.toString()
                binding.temp.text = state.weatherData.temperature.toString()

            }
            is AppState.Loading -> {
                binding.loadingLayout.isVisible = true
                binding.mainView.isVisible = false
            }
            is AppState.Error -> {
                Snackbar
                    .make(binding.root, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getWeather() }
                    .show()

            }
        }
    }

    private fun setData(weatherData: Weather) {
        binding.cityName.text = weatherData.city.city
        binding.cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                weatherData.city.lat.toString(),
                weatherData.city.lon.toString()
        )
        binding.temperatureValue.text = weatherData.temperature.toString()
        binding.feelsLikeValue.text = weatherData.feelsLike.toString()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}