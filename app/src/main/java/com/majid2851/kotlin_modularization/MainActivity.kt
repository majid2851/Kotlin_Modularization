package com.majid2851.kotlin_modularization

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader
import com.majid2851.core.DataState
import com.majid2851.core.Logger
import com.majid2851.core.ProgressBarState
import com.majid2851.core.UIComponent
import com.majid2851.hero_interactors.HeroInteractors
import com.majid2851.kotlin_modularization.ui.theme.DotaInfoTheme
import com.majid2851.ui_herolist.ui.HeroList
import com.majid2851.ui_herolist.ui.HeroListState
import com.majid2851.ui_herolist.ui.HeroListViewModel
import com.squareup.sqldelight.android.AndroidSqliteDriver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity()
{
    @Inject
    lateinit var imageLoader:ImageLoader



    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)



        setContent {
            DotaInfoTheme()
            {
                val viewModel:HeroListViewModel by viewModels()
                HeroList(
                    state = viewModel.state.value,
                    imageLoader=imageLoader
                )
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