package com.majid2851.ui_herodetail.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun HeroDetail(
    heroDetailState: HeroDetailState
) {
     heroDetailState.hero?.let {
        Text(text = it.localizedName)
    }?: Text(text = "Loading....")
}