package com.majid2851.hero_interactors

import com.majid2851.hero_datasource.cache.HeroCache
import com.majid2851.hero_datasource.network.HeroService
import com.squareup.sqldelight.db.SqlDriver

class HeroInteractors(
    val getHeros: GetHeros
    //TODO(ADD other hero interactors)
)
{
    companion object Factory{
        fun build(sqlDriver: SqlDriver):HeroInteractors{
            val service=HeroService.build()
            val cache=HeroCache.build(sqlDriver)

            return HeroInteractors(
                getHeros = GetHeros(
                    service=service,
                    heroCache = cache
                )
            )
        }


    }




}