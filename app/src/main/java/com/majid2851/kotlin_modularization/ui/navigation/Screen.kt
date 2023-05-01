package com.majid2851.kotlin_modularization.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route:String,
    val arguments:List<NamedNavArgument>
)
{
    object HeroList:Screen(
        route="heroList",
        arguments = emptyList()
    )

    object HeroDetailList:Screen(
        route = "heroDetail",
        arguments = listOf(
            navArgument("heroId"){
                type= NavType.IntType
            }
        )
    )

}