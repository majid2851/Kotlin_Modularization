package com.majid2851.kotlin_modularization

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.majid2851.core.DataState
import com.majid2851.core.Logger
import com.majid2851.core.ProgressBarState
import com.majid2851.core.UIComponent
import com.majid2851.hero_domain.Hero
import com.majid2851.hero_interactors.HeroInteractors
import com.majid2851.kotlin_modularization.ui.theme.DotaInfoTheme
import com.squareup.sqldelight.android.AndroidSqliteDriver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : ComponentActivity()
{
    private val heros:MutableState<List<Hero>> =
        mutableStateOf(listOf())
    private val progressBarState
        :MutableState<ProgressBarState> = mutableStateOf(
            ProgressBarState.Idle
        )
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        val getHeros=HeroInteractors.build(
            sqlDriver = AndroidSqliteDriver(
                schema = HeroInteractors.schema,
                context = this,
                name = HeroInteractors.dbName
            )
        ).getHeros
        val logger= Logger("GetHerosTest")
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
                    heros.value=it.data ?: listOf()
                }
                is DataState.Loading->{
                    progressBarState.value=it.progressBarState
                }

            }
        }.launchIn(CoroutineScope(IO))

        setContent {
            DotaInfoTheme()
            {
//                HeroList()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DotaInfoTheme() {

    }
}