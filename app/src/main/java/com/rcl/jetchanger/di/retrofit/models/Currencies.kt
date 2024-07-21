package com.rcl.jetchanger.di.retrofit.models

data class Currencies(
    val list: List<CurrencyModel>
)

data class CurrencyModel(
    val key: String,
    val fullName: String
)

data class CurrencyPair(
    val first: String,
    val second: String
)