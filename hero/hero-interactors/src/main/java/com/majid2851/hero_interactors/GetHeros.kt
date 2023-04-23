package com.majid2851.hero_interactors

import com.majid2851.core.DataState
import com.majid2851.core.ProgressBarState
import com.majid2851.core.UIComponent
import com.majid2851.hero_datasource.network.HeroService
import com.majid2851.hero_domain.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge

class GetHeros(
    private val service:HeroService
    //TODO => ADD CACHING
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

            //TODO(CACHING)

            emit(DataState.Data(data = heros))


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