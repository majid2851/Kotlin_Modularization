package com.majid2851.ui_herolist.ui

sealed class HeroListEvents
{
    object GetHeros:HeroListEvents()


    object FilterHeros:HeroListEvents()

    data class UpdateHeroName(
        val heroName:String
    ):HeroListEvents()


}
