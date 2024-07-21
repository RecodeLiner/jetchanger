package com.rcl.jetchanger.di.retrofit

import com.rcl.jetchanger.di.retrofit.models.Currencies
import retrofit2.http.GET

interface ApiService {
    @GET("api/currencies.json")
    suspend fun getAllCurrencies() : Currencies
}