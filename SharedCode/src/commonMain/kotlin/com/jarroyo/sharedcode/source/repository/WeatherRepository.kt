package com.jarroyo.sharedcode.repository

import com.jarroyo.kotlinmultiplatform.source.network.WeatherApi
import com.jarroyo.sharedcode.ApplicationDispatcher
import com.jarroyo.sharedcode.domain.Response
import com.jarroyo.sharedcode.domain.model.CurrentWeather
import com.jarroyo.sharedcode.domain.model.Location
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherRepository(
    private val weatherApi: WeatherApi
) {

    suspend fun getWeather(location: Location): Response<CurrentWeather> {
        return weatherApi.getWeather(location)
    }

    fun getCurrentWeather(location: Location, success: (CurrentWeather) -> Unit, failure: (Throwable?) -> Unit) {
        GlobalScope.launch(ApplicationDispatcher) {
            weatherApi.getCurrentWeather(location, success, failure)
        }
    }
}