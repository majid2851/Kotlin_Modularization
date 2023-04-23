package com.majid2851.hero_interactors

import com.majid2851.hero_datasource.network.HeroService

class HeroInteractors(
    val getHeros: GetHeros
    //TODO(ADD other hero interactors)
)
{
    companion object Factory{
        fun build():HeroInteractors{
            val service=HeroService.build()
            return HeroInteractors(
                getHeros = GetHeros(
                    service=service
                )
            )
        }


    }




}