package com.majid2851.ui_herolist.ui

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import coil.ImageLoader
import com.majid2851.core.ProgressBarState
import com.majid2851.core.UIComponentState
import com.majid2851.ui_herolist.components.HeroListFilter
import com.majid2851.ui_herolist.components.HeroListItem
import com.majid2851.ui_herolist.components.HeroListToolbar

@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class)
@Composable
fun HeroList(
    state: HeroListState,
    events: (HeroListEvents)->Unit,
    imageLoader:ImageLoader,
    navigateToDetailScreen:(Int)->Unit
)
{
    Box(
        modifier= Modifier.fillMaxSize(),
    )
    {
        Column()
        {

            HeroListToolbar(
                heroName = state.heroName,
                onHeroNameChanged = { heroName ->
                    events(HeroListEvents.UpdateHeroName(heroName))
                },
                onExecuteSearch = {
                    events(HeroListEvents.FilterHeros)
                },
                onShowFilterDialog = {
                    events(HeroListEvents.UpdateFilterDialogState(UIComponentState.Show))
                })


            LazyColumn()
            {
                Log.i("Sam2231-HeroList==>", "size:" + state.filterHeros.size.toString())
                items(state.filterHeros) { hero ->
                    HeroListItem(
                        hero = hero,
                        onSelectHero = { heroId ->
                            navigateToDetailScreen(heroId)
                        },
                        imageLoader = imageLoader
                    )


                }
            }
        }

        if (state.filterDialogState is UIComponentState.Show)
        {
            HeroListFilter(
                heroFilter = state.heroFilter,
                onUpdateHeroFilter = {
                    events(HeroListEvents.UpdateHeroFilter(it))
                },
                onCloseDialog ={
                    events(HeroListEvents.UpdateFilterDialogState(UIComponentState.Hide))
                },
                attributeFilter = state.primaryAttribute,
                onUpdateAttributeFilter = {
                    events(HeroListEvents.UpdateAttributeFilter(it))
                }
            )
        }

        if (state.progressBarState is ProgressBarState.Loading)
        {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }


    }
}