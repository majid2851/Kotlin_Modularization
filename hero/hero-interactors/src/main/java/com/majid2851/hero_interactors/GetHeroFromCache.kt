package com.majid2851.hero_interactors

import com.majid2851.core.DataState
import com.majid2851.core.ProgressBarState
import com.majid2851.core.UIComponent
import com.majid2851.hero_datasource.cache.HeroCache
import com.majid2851.hero_domain.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.xml.crypto.Data

class GetHeroFromCache(
    private val cache:HeroCache,
)
{
    fun execute(
        id:Int,
    ): Flow<DataState<Hero>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))

            val cacheHero=cache.getHero(id)
            if(cacheHero==null)
            {
                throw Exception("That hero does not exist in the cache")
            }
            emit(DataState.Data(cacheHero))

        }catch (e:Exception){
            e.printStackTrace()
            emit(
                DataState.Response(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?:"Unknown Error"
                    )
                )
            )
        }
        finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }





}