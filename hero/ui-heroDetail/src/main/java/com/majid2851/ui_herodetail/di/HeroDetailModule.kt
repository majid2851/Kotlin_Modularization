package com.majid2851.ui_herodetail.di

import com.majid2851.hero_interactors.GetHeroFromCache
import com.majid2851.hero_interactors.HeroInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class )
object HeroDetailModule
{
    @Provides
    @Singleton
    fun provideGetHeroFromCache(
        interactors:HeroInteractors
    ):GetHeroFromCache
    {
        return interactors.getHeroFromCache
    }




}