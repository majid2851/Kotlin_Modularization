package com.majid2851.kotlin_modularization.di

import android.app.Application
import com.majid2851.hero_interactors.HeroInteractors
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroInteractorsModule
{
    const val DBInjectedName="heroAndroidSqlDriver"
    @Provides
    @Singleton
    @Named(DBInjectedName)//when you have multiple database you can give name to them
    fun provideAndroidDriver(app:Application):SqlDriver
    {
        return  AndroidSqliteDriver(
            schema = HeroInteractors.schema,
            context = app,
            name = HeroInteractors.dbName
        )
    }

    @Provides
    @Singleton
    fun provideHeroInteractors(
        @Named(DBInjectedName) sqlDriver: SqlDriver,
    ):HeroInteractors{
        return HeroInteractors.build(
            sqlDriver=sqlDriver
        )
    }

}