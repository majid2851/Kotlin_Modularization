package com.majid2851.ui_herolist.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.majid2851.core.FilterOrder
import com.majid2851.hero_domain.HeroFilter
import com.majid2851.ui_herolist.ui.test.TAG_HERO_FILTER_DIALOG
import com.majid2851.ui_herolist.ui.test.TAG_HERO_FILTER_DIALOG_DONE
import com.majid2851.ui_herolist.ui.test.TAG_HERO_FILTER_HERO_CHECKBOX

@ExperimentalAnimationApi
@Composable
fun HeroListFilter(
    heroFilter: HeroFilter,
    onUpdateHeroFilter: (HeroFilter) -> Unit,
    onCloseDialog: () -> Unit,
){
    AlertDialog(
        modifier = Modifier
            .padding(16.dp)
            .testTag(TAG_HERO_FILTER_DIALOG)
        ,
        onDismissRequest = {
            onCloseDialog()
        },
        title = {
            Text(
                text = "Filter",
                style = MaterialTheme.typography.titleMedium,
            )
        },
        text = {
            LazyColumn {
                item{
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ){

                        // Spacer isn't working for some reason so use Row to create space
                        EmptyRow()

                        // Hero Filter
                        HeroFilterSelector(
                            filterOnHero = {
                                onUpdateHeroFilter(HeroFilter.Hero())
                            },
                            isEnabled = heroFilter is HeroFilter.Hero,
                            order = if(heroFilter is HeroFilter.Hero) heroFilter.order else null,
                            orderDesc = {
                                onUpdateHeroFilter(
                                    HeroFilter.Hero(
                                        order = FilterOrder.Descending
                                    )
                                )
                            },
                            orderAsc = {
                                onUpdateHeroFilter(
                                    HeroFilter.Hero(
                                        order = FilterOrder.Ascending
                                    )
                                )
                            }
                        )
                    }
                }
            }
        },
        confirmButton = {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Row( // make the icon larger so it's easier to click
                    modifier = Modifier
                        .align(Alignment.End)
                        .testTag(TAG_HERO_FILTER_DIALOG_DONE)
                        .clickable {
                            onCloseDialog()
                        }
                    ,
                ){
                    Icon(
                        modifier = Modifier
                            .padding(10.dp)
                        ,
                        imageVector = Icons.Default.Check,
                        contentDescription = "Done",
                        tint = Color(0xFF009a34)
                    )
                }

            }
        }
    )
}

/**
 * @param filterOnHero: Set the HeroFilter to 'Hero'
 * @param isEnabled: Is the Hero filter the selected 'HeroFilter'
 * @param order: Ascending or Descending?
 * @param orderDesc: Set the order to descending.
 * @param orderAsc: Set the order to ascending.
 */
@ExperimentalAnimationApi
@Composable
fun HeroFilterSelector(
    filterOnHero: () -> Unit,
    isEnabled: Boolean,
    order: FilterOrder? = null,
    orderDesc: () -> Unit,
    orderAsc: () -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .testTag(TAG_HERO_FILTER_HERO_CHECKBOX)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null, // disable the highlight
                    enabled = true,
                    onClick = {
                        filterOnHero()
                    },
                )
            ,
        ){
            Checkbox(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterVertically)
                ,
                checked = isEnabled,
                onCheckedChange = {
                    filterOnHero()
                },
                colors = CheckboxDefaults.colors(MaterialTheme.colorScheme.primary)
            )
            Text(
                text = HeroFilter.Hero().uiValue,
                style = MaterialTheme.typography.titleMedium,
            )
        }

        OrderSelector(
            descString = "z -> a",
            ascString = "a -> z",
            isEnabled = isEnabled,
            isDescSelected = isEnabled && order is FilterOrder.Descending,
            isAscSelected = isEnabled && order is FilterOrder.Ascending,
            onUpdateHeroFilterDesc = {
                orderDesc()
            },
            onUpdateHeroFilterAsc = {
                orderAsc()
            },
        )
    }
}

