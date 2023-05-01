package com.majid2851.hero_domain

import com.majid2851.core.FilterOrder

sealed class HeroFilter(val uiValue:String)
{
    data class Hero(
        val order:FilterOrder= FilterOrder.Descending
    ):HeroFilter("Hero")

    data class ProWins(
        val order:FilterOrder= FilterOrder.Descending
    ):HeroFilter("Pro win-rate")


}