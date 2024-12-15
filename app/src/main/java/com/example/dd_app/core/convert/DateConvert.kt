package com.example.dd_app.core.convert

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateConvert {
    private val defaultFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", Locale("ru"))

    fun stringToDate(dateString: String, formatter: DateTimeFormatter = defaultFormatter): LocalDateTime =
        LocalDateTime.parse(dateString, formatter)

    fun dateToString(date: LocalDateTime, formatter: DateTimeFormatter = defaultFormatter): String =
        date.format(formatter)

    fun format(date: LocalDateTime, pattern: String, locale: Locale = Locale("ru")): String =
        date.format(DateTimeFormatter.ofPattern(pattern, locale))
}