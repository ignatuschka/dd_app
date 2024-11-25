package com.example.dd_app.di

import android.content.Context
import com.example.dd_app.data.provider.DefaultResourceProvider
import com.example.dd_app.domain.provider.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider {
        return DefaultResourceProvider(context)
    }
}
