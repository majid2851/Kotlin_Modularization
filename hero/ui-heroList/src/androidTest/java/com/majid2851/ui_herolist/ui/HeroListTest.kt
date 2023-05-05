package com.majid2851.ui_herolist.ui

import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import coil.ImageLoader
import com.majid2851.hero_datasource_test.network.data.HeroDataValid
import com.majid2851.hero_datasource_test.serialzeHeroData
import com.majid2851.ui_herolist.coil.FakeImageLoader
import org.junit.Rule
import org.junit.Test

class HeroListTest
{

    @get:Rule
    val composeTestRule= createComposeRule()

    private val context=InstrumentationRegistry.getInstrumentation().targetContext
    private val imageLoader:ImageLoader = FakeImageLoader.build(context)
    private val heroData= serialzeHeroData(HeroDataValid.data)

    @Test
    fun areHerosShown()
    {
        composeTestRule.setContent {
            val state=remember{
                HeroListState(
                    heros = heroData,
                    filterHeros = heroData
                )
            }
            HeroList(
                state=state,
                events = {},
                navigateToDetailScreen = {},
                imageLoader = imageLoader
            )

            composeTestRule.onNodeWithText(
                "Zeus"
            ).assertIsDisplayed()
        }
    }


}