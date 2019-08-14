package com.jarroyo.sharedcode.di

import com.jarroyo.kotlinmultiplatform.source.network.WeatherApi
import com.jarroyo.sharedcode.domain.usecase.weather.getWeather.GetWeatherByNameUseCase
import com.jarroyo.sharedcode.presentation.WeatherPresenter
import com.jarroyo.sharedcode.repository.WeatherRepository
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object InjectorCommon {

    /**
     * WEATHER
     */
    private val weatherApi: WeatherApi by lazy { WeatherApi() }


    private val weatherRepository: WeatherRepository by lazy {
        WeatherRepository(weatherApi)
    }

    fun provideGetWeatherUseCase(): GetWeatherByNameUseCase {
        return GetWeatherByNameUseCase(weatherRepository)
    }

    /**
     * PRESENTER
     */

    fun provideWeatherPresenter(): WeatherPresenter {
        return WeatherPresenter(provideGetWeatherUseCase())
    }

}
