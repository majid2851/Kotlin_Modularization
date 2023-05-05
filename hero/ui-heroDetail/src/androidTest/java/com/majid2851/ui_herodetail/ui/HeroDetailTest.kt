package com.majid2851.ui_herolist.ui

import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import coil.ImageLoader
import com.majid2851.hero_datasource_test.network.data.HeroDataValid
import com.majid2851.hero_datasource_test.serialzeHeroData
import com.majid2851.ui_herodetail.coil.FakeImageLoader
import com.majid2851.ui_herodetail.ui.HeroDetail
import com.majid2851.ui_herodetail.ui.HeroDetailState
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

class HeroDetailTest
{

    @get:Rule
    val composeTestRule= createComposeRule()

    private val context=InstrumentationRegistry.getInstrumentation().targetContext
    private val imageLoader:ImageLoader = FakeImageLoader.build(context)
    private val heroData= serialzeHeroData(HeroDataValid.data)

    @Test
    fun isHeroDetailShown()
    {
        val hero=heroData.get(
             Random.nextInt(0,heroData.size-1)
         )
        composeTestRule.setContent {
           val state=remember{
                HeroDetailState(
                    hero=hero
                )
           }
            HeroDetail(
                state = state,
                event = {},
                imageLoader = imageLoader

                )
        }
        composeTestRule.onNodeWithText(
            hero.localizedName
        ).assertIsDisplayed()
    }


}