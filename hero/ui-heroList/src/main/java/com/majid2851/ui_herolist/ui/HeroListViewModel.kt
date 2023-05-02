package com.majid2851.ui_herolist.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.majid2851.core.DataState
import com.majid2851.core.Logger
import com.majid2851.core.UIComponent
import com.majid2851.hero_domain.HeroAttribute
import com.majid2851.hero_domain.HeroFilter
import com.majid2851.hero_interactors.FilterHeros
import com.majid2851.hero_interactors.GetHeros
import com.majid2851.ui_herolist.di.HeroListModule.HERO_LIST_LOGGER
import com.majid2851.ui_herolist.di.HeroListModule.filterHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val getHeros: GetHeros,
    private val filterHero:FilterHeros,
    private @Named(HERO_LIST_LOGGER) val logger: Logger
):ViewModel()
{

    val state: MutableState<HeroListState> =
        mutableStateOf(HeroListState())

    init {
       onTrigerEvent(HeroListEvents.GetHeros)


      Log.i("Sam2231-ViewModel==>","size:"+state.value.filterHeros.size.toString())
    }

    fun onTrigerEvent(event:HeroListEvents)
    {
        when(event)
        {
            is HeroListEvents.GetHeros ->{
                getHeros()
            }
            is HeroListEvents.FilterHeros ->{
                filterHeros()
                Log.i("Sam2231-ViewModelFilter==>","size:"+state.value.filterHeros.size.toString())
            }
            is HeroListEvents.UpdateHeroName ->{
                updateHeroName(event.heroName)
            }
            is HeroListEvents.UpdateHeroFilter ->{
                updateHeroFilter(event.heroFilter)
            }
            is HeroListEvents.UpdateFilterDialogState ->{
                state.value=state.value.copy(filterDialogState = event.uiComponentState)
            }
            is HeroListEvents.UpdateAttributeFilter->{
                updateAttributeFilter(event.attribute)
            }

            else -> {}
        }

    }

    private fun updateAttributeFilter(attribute: HeroAttribute)
    {
        state.value=state.value.copy(primaryAttribute = attribute)
        filterHeros()
    }

    private fun updateHeroFilter(heroFilter: HeroFilter) {
        state.value=state.value.copy(heroFilter = heroFilter)
        filterHeros()

    }

    private fun filterHeros() {
        val filteredList=filterHero.excecute(
            current = state.value.heros,
            heroName = state.value.heroName,
            heroFilter = state.value.heroFilter,
            attrFilter = state.value.primaryAttribute
        )
        state.value=state.value.copy(filterHeros = filteredList)
    }

    private fun updateHeroName(heroName: String) {
        state.value=state.value.copy(heroName=heroName)
    }


    private fun getHeros()
    {
        getHeros.excecute().onEach {
            when(it)
            {
                is DataState.Response->{
                    when(it.uiComponent)
                    {
                        is UIComponent.Dialog->{
                            logger.log((it.uiComponent as UIComponent.Dialog).description)
                        }
                        is UIComponent.None->{
                            logger.log((it.uiComponent as UIComponent.None).message)
                        }
                    }
                }

                is DataState.Data->{
                    state.value=state.value.copy(heros = it.data ?: listOf())
                    Log.i("Sam2231-dataBeforFilter==>","size:"+state.value.filterHeros.size.toString())
                    filterHeros()

                    Log.i("Sam2231-dataBeforFilter==>","size:"+state.value.filterHeros.size.toString())
                }
                is DataState.Loading->{
                    state.value=state.value.copy(progressBarState = it.progressBarState)
                }

            }
        }.launchIn(viewModelScope)
    }



}