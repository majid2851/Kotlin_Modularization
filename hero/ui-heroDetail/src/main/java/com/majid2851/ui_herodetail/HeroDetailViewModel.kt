package com.majid2851.ui_herodetail

import androidx.lifecycle.ViewModel
import com.majid2851.hero_interactors.GetHeroFromCache
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel @Inject
    constructor(
        private val getHeroFromCache: GetHeroFromCache
    ):ViewModel()
{



}