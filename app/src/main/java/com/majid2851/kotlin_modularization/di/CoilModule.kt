package com.majid2851.kotlin_modularization.di

import android.app.Application
import coil.ImageLoader
import com.majid2851.kotlin_modularization.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoilModule
{
    @Provides
    @Singleton
    fun provideImageLoader(app:Application):ImageLoader
    {
       return ImageLoader.Builder(app)
             .error(R.drawable.error_image)
             .placeholder(R.drawable.white_background)
             .crossfade(true)
             .build()

    }


}