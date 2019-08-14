package com.jarroyo.mppapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jarroyo.sharedcode.di.InjectorCommon
import com.jarroyo.sharedcode.domain.model.CurrentWeather
import com.jarroyo.sharedcode.domain.model.Location
import com.jarroyo.sharedcode.presentation.WeatherPresenter
import com.jarroyo.sharedcode.presentation.WeatherView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), WeatherView {

    lateinit var mPresenter: WeatherPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPresenter()
        configView()
    }

    private fun configView() {
        activity_main_button_getweather.setOnClickListener {

            val locationName = activity_main_et_location.text.toString()
            if (locationName.isNullOrEmpty()){
                activity_main_tv_result.text = "Type a location please"
            } else {
                mPresenter.getCurrentWeather(Location(locationName))
            }

        }
    }

    private fun initPresenter() {
        mPresenter = InjectorCommon.provideWeatherPresenter()
        mPresenter.attachView(this)
    }

    override fun onErrorGetCurrentWeather(throwable: Throwable) {
    }

    override fun onSuccessGetCurrentWeather(currentWeather: CurrentWeather) {
        activity_main_tv_result.text = currentWeather.toString()
    }

    override fun showHideLoading(visible: Boolean) {
    }
}
