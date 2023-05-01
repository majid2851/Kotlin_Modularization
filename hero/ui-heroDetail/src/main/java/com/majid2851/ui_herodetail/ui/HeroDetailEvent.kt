package com.majid2851.ui_herodetail.ui

sealed class HeroDetailEvent
{
    data class GetHeroFromCache(
        val id:Int
    ):HeroDetailEvent()


}