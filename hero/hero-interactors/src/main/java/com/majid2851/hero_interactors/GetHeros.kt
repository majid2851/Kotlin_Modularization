package com.majid2851.hero_interactors

import com.majid2851.core.DataState
import com.majid2851.core.ProgressBarState
import com.majid2851.core.UIComponent
import com.majid2851.hero_datasource.cache.HeroCache
import com.majid2851.hero_datasource.network.HeroService
import com.majid2851.hero_domain.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge

class GetHeros(
    private val heroCache: HeroCache,
    private val service:HeroService
)
{
    fun excecute():Flow<DataState<List<Hero>>> = flow{
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            val heros:List<Hero> = try {
                service.getHeroStats()
            }catch (e:Exception){
                e.printStackTrace()
                emit(
                    DataState.Response(
                        uiComponent = UIComponent.Dialog(
                            title = "Network Data ERROR",
                            description = e.message?:"Unknown Error"
                        )
                    )
                )
                listOf()
            }
            heroCache.insert(heros)
            val cachedHeros=heroCache.selectAll()

            emit(DataState.Data(data = cachedHeros))

//            throw Exception("You will achieve london position")

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