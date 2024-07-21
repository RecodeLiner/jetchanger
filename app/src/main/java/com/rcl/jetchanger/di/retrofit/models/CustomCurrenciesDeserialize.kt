package com.rcl.jetchanger.di.retrofit.models

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CustomCurrenciesDeserialize : JsonDeserializer<Currencies> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Currencies {
        val jsonObject = json?.asJsonObject
        val map = mutableListOf<CurrencyModel>()
        jsonObject?.entrySet()?.forEach { entry ->
            map.add(CurrencyModel(entry.key, entry.value.asString))
        }
        return Currencies(map)
    }
}