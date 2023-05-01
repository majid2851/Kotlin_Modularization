package com.majid2851.hero_interactors

import com.majid2851.hero_datasource.cache.HeroCache
import com.majid2851.hero_datasource.network.HeroService
import com.squareup.sqldelight.db.SqlDriver

class HeroInteractors(
    val getHeros: GetHeros,
    val getHeroFromCache: GetHeroFromCache,
    val filterHeros: FilterHeros
)
{
    companion object Factory{
        fun build(sqlDriver: SqlDriver):HeroInteractors{
            val service=HeroService.build()
            val cache=HeroCache.build(sqlDriver)

            return HeroInteractors(
                getHeros = GetHeros(
                    service=service,
                    heroCache = cache,
                ),
                getHeroFromCache = GetHeroFromCache(
                    cache=cache
                ),
                filterHeros = FilterHeros()
            )
        }

        val schema: SqlDriver.Schema = HeroCache.schema

        val dbName: String = HeroCache.dbName


    }

}