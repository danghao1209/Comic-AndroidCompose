package com.app.comicapp.di

import com.app.comicapp.data.entities.Comic
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson

class ComicnAdapter : JsonAdapter<Comic>() {
    @FromJson
    override fun fromJson(reader: JsonReader): Comic? {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Comic> = moshi.adapter(Comic::class.java)
        return adapter.fromJson(reader)
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Comic?) {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Comic> = moshi.adapter(Comic::class.java)
        adapter.toJson(writer, value)
    }
}