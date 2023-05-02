package com.majid2851.ui_herolist.ui

import com.majid2851.core.UIComponentState
import com.majid2851.hero_domain.HeroFilter

sealed class HeroListEvents
{
    object GetHeros:HeroListEvents()

    object FilterHeros:HeroListEvents()

    data class UpdateHeroName(
        val heroName:String
    ):HeroListEvents()

    data class UpdateHeroFilter(
        val heroFilter: HeroFilter
    ):HeroListEvents()

    data class UpdateFilterDialogState(
        val uiComponentState: UIComponentState
    ):HeroListEvents()



}
