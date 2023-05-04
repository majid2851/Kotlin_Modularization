package com.majid2851.ui_herodetail.ui

import com.majid2851.core.ProgressBarState
import com.majid2851.core.Queue
import com.majid2851.core.UIComponent
import com.majid2851.hero_domain.Hero

data class HeroDetailState(
    val progressBarState:ProgressBarState=ProgressBarState.Idle,
    val hero: Hero?=null,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf())
)
{

}