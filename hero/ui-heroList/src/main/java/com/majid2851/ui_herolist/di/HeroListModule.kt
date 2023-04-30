package com.majid2851.ui_herolist.di

import com.majid2851.hero_interactors.GetHeros
import com.majid2851.hero_interactors.HeroInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HeroListModule
{
    @Provides
    @Singleton
    fun provideGetHeros(
        interactors:HeroInteractors
    ):GetHeros
    {
        return interactors.getHeros
    }




}