package com.majid2851.kotlin_modularization

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import com.majid2851.kotlin_modularization.ui.navigation.Screen
import com.majid2851.kotlin_modularization.ui.theme.DotaInfoTheme
import com.majid2851.ui_herodetail.ui.HeroDetail
import com.majid2851.ui_herodetail.ui.HeroDetailViewModel
import com.majid2851.ui_herolist.ui.HeroList
import com.majid2851.ui_herolist.ui.HeroListViewModel
import dagger.hilt.android.AndroidEntryPoint
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
                val navController= rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.HeroList.route,
                    builder = {
                        addHeroList(
                            navController = navController,
                            imageLoader=imageLoader
                        )
                        addHeroDetail()

                    })

            }
        }
    }



}

fun NavGraphBuilder.addHeroDetail() {
    composable(
        route = Screen.HeroDetailList.route + "/{heroId}",
        arguments = Screen.HeroDetailList.arguments
    )
    {
        val viewModel:HeroDetailViewModel = hiltViewModel()
        HeroDetail(viewModel.state.value)
    }
}


fun NavGraphBuilder.addHeroList(
    navController: NavHostController,
    imageLoader: ImageLoader
) {
    composable(route = Screen.HeroList.route)
    {
        val viewModel: HeroListViewModel = hiltViewModel()
        HeroList(
            state = viewModel.state.value,
            imageLoader = imageLoader,
            navigateToDetailScreen = { heroId ->
                navController.navigate(
                    "${Screen.HeroDetailList.route}/$heroId"
                )
            }
        )
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