package com.majid2851.ui_herolist.ui

import com.majid2851.core.ProgressBarState
import com.majid2851.hero_domain.Hero
import com.majid2851.hero_domain.HeroAttribute
import com.majid2851.hero_domain.HeroFilter

data class HeroListState(
    val progressBarState:ProgressBarState= ProgressBarState.Idle,
    val heros:List<Hero> = listOf(),
    val filterHeros:List<Hero> = listOf(),
    val heroName:String="",
    val heroFilter: HeroFilter=HeroFilter.Hero(),
    val primaryAttribute:HeroAttribute = HeroAttribute.Unknown
)