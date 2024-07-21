package com.rcl.jetchanger.di.retrofit.models

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CustomSerial : JsonDeserializer<Currencies> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Currencies {
        val jsonObject = json?.asJsonObject
        val map = arrayListOf<String>()
        if (jsonObject != null) {
            jsonObject.entrySet().forEach { entry ->
                map.add(entry.key)
            }
        }
        return Currencies(map)
    }
}