@file:OptIn(ExperimentalAnimationApi::class)

package com.majid2851.kotlin_modularization

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.majid2851.kotlin_modularization.ui.navigation.Screen
import com.majid2851.kotlin_modularization.ui.theme.DotaInfoTheme
import com.majid2851.ui_herodetail.ui.HeroDetail
import com.majid2851.ui_herodetail.ui.HeroDetailViewModel
import com.majid2851.ui_herolist.ui.HeroList
import com.majid2851.ui_herolist.ui.HeroListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@OptIn(ExperimentalAnimationApi::class)
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
                        addHeroDetail(imageLoader=imageLoader)

                    })

            }
        }
    }



}

fun NavGraphBuilder.addHeroDetail(imageLoader:ImageLoader) {
    composable(
        route = Screen.HeroDetailList.route + "/{heroId}",
        arguments = Screen.HeroDetailList.arguments,
//        enterTransition={_,_ ->
//            slideInHorizontally (
//                initialOffsetX = {300},
//                animationSpec = tween(
//                    durationMillis = 300,
//                    easing = FastOutSlowInEasing,
//                )
//            ) + fadeIn(animationSpec = tween(300))
//        },
//        popExitTransition = {_,_ ->
//            slideOutHorizontally(
//                targetOffsetX = {300},
//                animationSpec = tween(
//                    durationMillis = 300,
//                    easing = FastOutSlowInEasing,
//                )
//            ) + fadeOut(animationSpec = tween(300))
//        }
    )
    {
        val viewModel:HeroDetailViewModel = hiltViewModel()
        HeroDetail(
            state = viewModel.state.value,
            imageLoader = imageLoader,
            event = viewModel::onTriggerEvent
        )
    }
}


fun NavGraphBuilder.addHeroList(
    navController: NavHostController,
    imageLoader: ImageLoader
) {
    composable(
        route = Screen.HeroList.route,
//        exitTransition = {_,_->
//            slideOutHorizontally(
//                targetOffsetX = {-300},
//                animationSpec = tween(
//                    durationMillis = 300,
//                    easing = FastOutSlowInEasing,
//                    )
//                ) + fadeOut(animationSpec = tween(300))
//        }, popEnterTransition = {_,_ ->
//            slideInHorizontally (
//                initialOffsetX = {-300},
//                animationSpec = tween(
//                    durationMillis = 300,
//                    easing = FastOutSlowInEasing,
//                )
//            ) + fadeIn(animationSpec = tween(300))
//        }

    )
    {
        val viewModel: HeroListViewModel = hiltViewModel()

        Log.i("Sam2231-MainActivity==>","size:"+viewModel.state.value.filterHeros.size.toString())
        HeroList(
            state = viewModel.state.value,
            events=viewModel::onTrigerEvent,
            imageLoader = imageLoader,
            navigateToDetailScreen = { heroId ->
                navController.navigate(
                    "${Screen.HeroDetailList.route}/$heroId"
                )
            }
        )
    }
}

