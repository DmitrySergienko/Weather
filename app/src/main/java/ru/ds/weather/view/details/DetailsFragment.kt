package ru.ds.weather.view.details

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.fragment_details.*
import ru.ds.weather.R
import ru.ds.weather.databinding.FragmentDetailsBinding
import ru.ds.weather.model.Weather
import ru.ds.weather.showSnackBar
import ru.ds.weather.model.AppState
import ru.ds.weather.model.City
import ru.ds.weather.viewmodel.DetailsViewModel


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: Weather


    private val viewModel: DetailsViewModel by lazy { ViewModelProvider(this)[DetailsViewModel::class.java] }
    


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Weather()

        binding.mainView.visibility = View.GONE
        binding.loadingLayout.visibility = View.VISIBLE
        viewModel.detailsLiveData.observe(viewLifecycleOwner, Observer { renderData(it) })

        getWeather()
    }

    private fun getWeather() {
        binding.mainView.visibility = View.GONE
        binding.loadingLayout.visibility = View.VISIBLE
        viewModel.getWeatherFromRemoteSource(weatherBundle.city.lat, weatherBundle.city.lon)

    }

    //Метод renderData теперь обрабатывает состояние приложения и обеспечивает корректное отображение на экране
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.mainView.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
                setWeather(appState.weatherData[0])
            }
            is AppState.Loading -> {
                binding.mainView.visibility = View.GONE
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.mainView.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
                binding.mainView.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    {getWeather() })
            }
        }
    }
//setWeather отображает данные в фрагменте
    private fun setWeather(weather: Weather) {

        val city = weatherBundle.city
        binding.cityName.text = city.city
        binding.cityCoordinates.text = String.format(
            getString(R.string.city_coordinates),
            city.lat.toString(),
            city.lon.toString()
        )
        binding.temperatureValue.text = weather.temperature.toString()
        binding.feelsLikeValue.text = weather.feelsLike.toString()
        binding.weatherCondition.text = weather.condition

//picture upload through Glide library

    Glide.with(requireContext())
        .load("https://freepngimg.com/thumb/city/86758-building-city-2d-game-computer-video-graphics.png")
        .into(binding.imPicture)
    weather.icon?.let {
        GlideToVectorYou.justLoadImage(
            activity,
            Uri.parse("https://yastatic.net/weather/i/icons/blueye/color/svg/${it}.svg"),
            weatherIcon
        )
    }
    //вызфываем меирд для сохранения истории запроссов погоды
    saveCity(weatherBundle.city,weather)

}

    private fun saveCity(
        city: City,
        weather: Weather
    ) {
        viewModel.saveCityToDB(
            Weather(
                city,
                weather.temperature,
                weather.feelsLike,
                weather.condition
            )
        )
    }


    companion object {
        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
