package com.majid2851.ui_herodetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun HeroDetail(
    heroId: Int?
) {
    Text("id:${heroId}")
}