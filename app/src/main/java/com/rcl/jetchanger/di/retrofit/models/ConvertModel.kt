package com.rcl.jetchanger.di.retrofit.models

data class ConvertFromUsd(
    val disclaimer: String,
    val license: String,
    val timestamp: Int,
    val base: String,
    val rates: Rates
)

data class Rates(
    val code: String,
    val value : String
)