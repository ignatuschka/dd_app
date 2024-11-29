package com.example.dd_app.domain.provider

interface ResourceProvider {
    fun getString(resourceId: Int): String
}