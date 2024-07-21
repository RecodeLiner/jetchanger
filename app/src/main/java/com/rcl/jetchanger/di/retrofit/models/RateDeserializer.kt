package com.rcl.jetchanger.di.retrofit.models

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class RateDeserializer : JsonDeserializer<Rates> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Rates {
        val jsonObject = json?.asJsonObject
        if (jsonObject?.entrySet()?.size == 1) {
            return Rates(
                code = jsonObject.entrySet()!!.first().key,
                value = jsonObject.entrySet()!!.first().value.asString
            )
        }
        else {
            throw Exception()
        }
    }
}