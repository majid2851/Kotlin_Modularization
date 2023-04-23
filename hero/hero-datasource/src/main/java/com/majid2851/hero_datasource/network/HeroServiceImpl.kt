package com.majid2851.hero_datasource.network

import com.majid2851.hero_domain.Hero
import io.ktor.client.HttpClient
import io.ktor.client.request.*

class HeroServiceImpl(
    private val httpClient:HttpClient,
):HeroService
{
    override suspend fun getHeroStats(): List<Hero> {
         return httpClient.get<List<HeroDto>>{
            url(EndPoints.HERO_STATS)
         }.map { it.toHero() }
    }


}