package com.example.dd_app.core.convert

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class DateConvert {
    private val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", Locale("ru"))
    fun stringToDate(dateString: String) : LocalDateTime = LocalDateTime.parse(dateString, formatter)
    fun dateToString(date: LocalDateTime) : String = date.format(formatter)
    fun format(date: LocalDateTime, pattern: String) : String = date.format(DateTimeFormatter.ofPattern(pattern, Locale("ru")))
}