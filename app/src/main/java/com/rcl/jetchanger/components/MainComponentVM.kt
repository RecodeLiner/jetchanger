package com.rcl.jetchanger.components

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rcl.jetchanger.di.retrofit.ApiService
import com.rcl.jetchanger.di.retrofit.models.CurrencyModel
import com.rcl.jetchanger.di.retrofit.models.CurrencyPair
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Locale.US

class MainComponentVM : ViewModel(), KoinComponent {
    val inputValue = mutableStateOf("0")
    val outputValue = mutableStateOf("0")
    private val apiService: ApiService by inject()
    val CurrenciesList = mutableStateListOf<CurrencyModel>()
    val selectedPairs = mutableStateOf(CurrencyPair("RUB", "RUB"))

    init {
        viewModelScope.launch {
            withContext(IO) {
                CurrenciesList.addAll(apiService.getAllCurrencies().list)
            }
        }
    }

    fun calculate() {
        viewModelScope.launch {
            val first = apiService.convertFromUsd(to = selectedPairs.value.first)
            val second = apiService.convertFromUsd(to = selectedPairs.value.second)
            updateOutputValue(
                ((first.rates.value.toDouble() * second.rates.value.toDouble())
                    * inputValue.value.toDouble()).toString()
            )
        }
    }

    fun updatePair(value: CurrencyPair) {
        selectedPairs.value = value
    }

    fun updateOutputValue(value: String, format: String = "%.3f") {
        outputValue.value = String.format(US, format, value)
    }

    fun updateInputValue(value: String) {
        if (value.split(",").size <= 2) {
            inputValue.value = value
        }
    }
}