package com.rcl.jetchanger.di.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.Strictness
import com.rcl.jetchanger.di.retrofit.models.Currencies
import com.rcl.jetchanger.di.retrofit.models.CustomCurrenciesDeserialize
import com.rcl.jetchanger.di.retrofit.models.RateDeserializer
import com.rcl.jetchanger.di.retrofit.models.Rates
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModel {
    private const val BASE_URL = "https://openexchangerates.org/"
    const val APP_ID = "75dcd615c3ab4bf3bbba42e78045bf7e"
    val retrofitModule = module {
        single {
            OkHttpClient()
        }
        single {
            GsonBuilder()
                .setStrictness(Strictness.LENIENT)
                .registerTypeAdapter(Currencies::class.java, CustomCurrenciesDeserialize())
                .registerTypeAdapter(Rates::class.java, RateDeserializer())
                .create()
        }
        single {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(get<OkHttpClient>())
                .addConverterFactory(GsonConverterFactory.create(get<Gson>()))
                .build()
        }
        single {
            get<Retrofit>().create(ApiService::class.java)
        }
    }
}