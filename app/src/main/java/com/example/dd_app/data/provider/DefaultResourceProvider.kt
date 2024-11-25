package com.example.dd_app.data.provider

import android.content.Context
import com.example.dd_app.domain.provider.ResourceProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultResourceProvider @Inject constructor(@ApplicationContext private val context: Context) :
    ResourceProvider {
    override fun getString(resourceId: Int): String = context.getString(resourceId)
}