package com.example.dd_app.core.convert

object DistanceConvert {
    fun toShortMeters(meters: Int) : String = if (meters > 1000) "${meters.toDouble() / 1000} км" else "$meters м"
}