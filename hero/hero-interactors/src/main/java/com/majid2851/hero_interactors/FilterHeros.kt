package com.majid2851.hero_interactors

import com.majid2851.core.DataState
import com.majid2851.core.FilterOrder
import com.majid2851.hero_domain.Hero
import com.majid2851.hero_domain.HeroAttribute
import com.majid2851.hero_domain.HeroFilter
import kotlinx.coroutines.flow.Flow
import kotlin.math.round

class FilterHeros
{
    fun excecute(
        current:List<Hero>,
        heroName:String,
        heroFilter: HeroFilter,
        attrFilter:HeroAttribute
    ): List<Hero>{
        var filterdList:MutableList<Hero> = current.filter {
            it.localizedName.lowercase().contains(heroName.lowercase())
        }.toMutableList()

        when(heroFilter)
        {
            is HeroFilter.Hero ->{
                when(heroFilter.order)
                {
                    is FilterOrder.Descending ->{
                        filterdList.sortByDescending { it.localizedName }
                    }
                    is FilterOrder.Ascending ->{
                        filterdList.sortBy { it.localizedName}
                    }
                    else -> {}
                }
            }


            is HeroFilter.ProWins ->{
                when(heroFilter.order)
                {
                    is FilterOrder.Descending ->{
                        filterdList.sortByDescending {
                            getWinRate(it.proWins.toDouble(),it.proPick.toDouble())
                         }
                    }
                    is FilterOrder.Ascending ->{
                        filterdList.sortBy {
                            getWinRate(it.proWins.toDouble(),it.proPick.toDouble())
                        }
                    }
                    else -> {}
                }



            }


            else -> {}
        }

        when(attrFilter)
        {
            is HeroAttribute.Strength ->{
                filterdList=filterdList.filter {
                    it.primaryAttribute is HeroAttribute.Strength
                }.toMutableList()
            }
            is HeroAttribute.Agility ->{
                filterdList=filterdList.filter {
                    it.primaryAttribute is HeroAttribute.Agility
                }.toMutableList()
            }
            is HeroAttribute.Intelligence ->{
                filterdList=filterdList.filter {
                    it.primaryAttribute is HeroAttribute.Intelligence
                }.toMutableList()
            }
            is HeroAttribute.Unknown ->{
                //don't filter
            }


            else -> {}
        }
        return filterdList


    }

    private fun getWinRate(proWins:Double,proPick:Double) = if (proPick <= 0) {
        0
    } else {
        val winRate: Int = round(
            proWins.toDouble() /
                    proPick.toDouble() * 100
        ).toInt()
        winRate
    }


}