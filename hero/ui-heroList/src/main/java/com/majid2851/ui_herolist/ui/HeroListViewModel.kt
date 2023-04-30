package com.majid2851.ui_herolist.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.majid2851.core.DataState
import com.majid2851.core.Logger
import com.majid2851.core.UIComponent
import com.majid2851.hero_interactors.GetHeros
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val getHeros: GetHeros,
):ViewModel()
{

    private val logger= Logger("HeroListViewModel")
    val state: MutableState<HeroListState> =
        mutableStateOf(HeroListState())

    init {
       getHeros()

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
                }
                is DataState.Loading->{
                    state.value=state.value.copy(progressBarState = it.progressBarState)
                }

            }
        }.launchIn(viewModelScope)
    }



}