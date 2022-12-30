package com.android.sample.tmdb.utils

import com.android.sample.tmdb.domain.model.TMDbItem
import com.google.gson.*
import java.lang.reflect.Type

private const val CLASSNAME = "CLASSNAME"
private const val DATA = "DATA"

class TMDbItemTypeAdapter
    : JsonSerializer<TMDbItem>, JsonDeserializer<TMDbItem> {
    override fun serialize(
        src: TMDbItem?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonObject().apply {
            addProperty(CLASSNAME, src?.javaClass?.name)
            add(DATA, context?.serialize(src))
        }
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): TMDbItem {
        val jsonObject = json?.asJsonObject
        val className = jsonObject?.get(CLASSNAME)?.asString
        val clazz = getObjectClass(className)
        return context!!.deserialize(jsonObject?.get(DATA), clazz)
    }

    private fun getObjectClass(className: String?): Class<*> {
        try {
            return Class.forName(className)
        } catch (e: ClassNotFoundException) {
            throw JsonParseException(e)
        }
    }
}