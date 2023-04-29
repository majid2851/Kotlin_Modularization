package com.majid2851.ui_herolist

import com.majid2851.core.ProgressBarState
import com.majid2851.hero_domain.Hero

data class HeroListState(
    val progressBarState:ProgressBarState= ProgressBarState.Idle,
    val heros:List<Hero> = listOf()
)