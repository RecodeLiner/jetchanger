package com.rcl.jetchanger.di.retrofit

import com.rcl.jetchanger.di.retrofit.RetrofitModel.APP_ID
import com.rcl.jetchanger.di.retrofit.models.ConvertFromUsd
import com.rcl.jetchanger.di.retrofit.models.Currencies
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/currencies.json")
    suspend fun getAllCurrencies(
        @Query("show_inactive") showInactive: Boolean = false
    ) : Currencies

    //free method
    @GET("api/latest.json")
    suspend fun convertFromUsd(
        @Query("app_id") appId: String = APP_ID,
        @Query("symbols") to: String,
        @Query("prettyprint") prettyPrint: Boolean = true
    ) : ConvertFromUsd
}