package com.rcl.jetchanger

import android.app.Application
import com.rcl.jetchanger.di.retrofit.RetrofitModel.retrofitModule
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                retrofitModule
            )
        }
    }
}