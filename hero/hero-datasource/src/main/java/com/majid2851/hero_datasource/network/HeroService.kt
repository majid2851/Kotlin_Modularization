package com.majid2851.hero_datasource.network

import com.majid2851.hero_domain.Hero
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.Json
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

interface HeroService
{
    suspend fun getHeroStats():List<Hero>

    companion object Factory{
        fun build():HeroService{
            return HeroServiceImpl(
                httpClient = HttpClient(Android){
                    install(JsonFeature){
                        serializer=KotlinxSerializer(
                            kotlinx.serialization.json.Json {
                                ignoreUnknownKeys=true //if the server returns extra field , ignore them
                            }
                        )
                    }
                }
            )
        }
    }



}