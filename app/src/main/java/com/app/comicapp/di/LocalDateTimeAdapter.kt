package com.app.comicapp.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class LocalDateTimeAdapter constructor(var FORMATTER: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME):
    JsonAdapter<LocalDateTime>() {
    @FromJson
    override fun fromJson(dateTimeString: JsonReader): LocalDateTime? {
        return LocalDateTime.parse(dateTimeString.readJsonValue().toString(), FORMATTER)
    }

    @ToJson
    override fun toJson(writer: JsonWriter, dateTime: LocalDateTime?) {
        writer.jsonValue(dateTime?.format(FORMATTER))
    }
}