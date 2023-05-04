package com.majid2851.ui_herodetail.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.majid2851.core.DataState
import com.majid2851.core.Logger
import com.majid2851.core.ProgressBarState
import com.majid2851.core.Queue
import com.majid2851.core.UIComponent
import com.majid2851.hero_interactors.GetHeroFromCache
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HeroDetailViewModel @Inject
    constructor(
        private val getHeroFromCache: GetHeroFromCache,
        private val savedStateHandle: SavedStateHandle,
//        private @Named(HERO_LIST_LOGGER)  val logger:Logger
    ):ViewModel()
{
    val state:MutableState<HeroDetailState> = mutableStateOf(HeroDetailState())

    init {
        savedStateHandle.get<Int>("heroId")?.let {id->
            onTriggerEvent(HeroDetailEvent.GetHeroFromCache(id))
        }

    }
    fun onTriggerEvent(event: HeroDetailEvent)
    {
        when(event)
        {
            is HeroDetailEvent.GetHeroFromCache ->{
                getHeroFromCache(event.id)
            }



        }

    }

    private fun getHeroFromCache(id: Int)
    {
        getHeroFromCache.execute(id).onEach {dataState->
            when(dataState)
            {
                is DataState.Loading ->{
                    state.value=state.value.copy(progressBarState = dataState.progressBarState)
                }
                is DataState.Data ->{
                    state.value=state.value.copy(hero = dataState.data)
                }
                is DataState.Response ->{
                    when(dataState.uiComponent)
                    {
                        is UIComponent.Dialog->{
                            appendToMessageQueue(dataState.uiComponent)
                        }
                        is UIComponent.None->{
//                            logger.log((dataState.uiComponent as UIComponent.None).message)
                        }
                    }
                }


                else -> {}
            }
        }.launchIn(viewModelScope)
    }


    private fun appendToMessageQueue(uiComponent: UIComponent)
    {
        val queue=state.value.errorQueue
        queue.add(uiComponent)
        state.value=state.value.copy(errorQueue = Queue(mutableListOf()))
        state.value=state.value.copy(errorQueue = queue)

    }

}