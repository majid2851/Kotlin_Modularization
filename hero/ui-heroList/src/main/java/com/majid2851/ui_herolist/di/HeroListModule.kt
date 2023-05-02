package com.majid2851.ui_herolist.di

import com.majid2851.core.Logger
import com.majid2851.hero_interactors.FilterHeros
import com.majid2851.hero_interactors.GetHeros
import com.majid2851.hero_interactors.HeroInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroListModule
{
    const val HERO_LIST_LOGGER="heroListLogger"
    @Provides
    @Singleton
    @Named(HERO_LIST_LOGGER)
    fun provideLogger():Logger
    {
        return Logger(
            tag = "HeroList",
            isDebug = true
        )
    }



    @Provides
    @Singleton
    fun provideGetHeros(
        interactors:HeroInteractors
    ):GetHeros
    {
        return interactors.getHeros
    }



    @Provides
    @Singleton
    fun filterHero(
        interactors:HeroInteractors
    ):FilterHeros
    {
        return interactors.filterHeros
    }



}