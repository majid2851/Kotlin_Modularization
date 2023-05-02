package com.majid2851.ui_herolist.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.majid2851.ui_herolist.ui.test.TAG_HERO_FILTER_ASC
import com.majid2851.ui_herolist.ui.test.TAG_HERO_FILTER_DESC

/**
 * @param descString: String displayed in the "descending" checkbox
 * @param ascString: String displayed in the "ascending" checkbox
 * @param isEnabled: Is this HeroFilter currently the selected HeroFilter?
 * @param isDescSelected: Is the "descending" checkbox selected?
 * @param isAscSelected: Is the "ascending" checkbox selected?
 * @param onUpdateHeroFilterDesc: Set the filter to Descending.
 * @param onUpdateHeroFilterAsc: Set the filter to Ascending.
 */
@ExperimentalAnimationApi
@Composable
fun OrderSelector(
    descString: String,
    ascString: String,
    isEnabled: Boolean,
    isDescSelected: Boolean,
    isAscSelected: Boolean,
    onUpdateHeroFilterDesc: () -> Unit,
    onUpdateHeroFilterAsc: () -> Unit,
){
    // Descending Order
    AnimatedVisibility(visible = isEnabled) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, bottom = 8.dp)
                .testTag(TAG_HERO_FILTER_DESC)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null, // disable the highlight
                    enabled = isEnabled,
                    onClick = {
                        onUpdateHeroFilterDesc()
                    },
                )
            ,
        ){
            Checkbox(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterVertically)
                ,
                enabled= isEnabled,
                checked = isEnabled && isDescSelected,
                onCheckedChange = {
                    onUpdateHeroFilterDesc()
                },
                colors = CheckboxDefaults.colors(MaterialTheme.colorScheme.primary)
            )
            Text(
                text = descString,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }

    // Ascending Order
    AnimatedVisibility(visible = isEnabled) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, bottom = 8.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null, // disable the highlight
                    enabled = isEnabled,
                    onClick = {
                        onUpdateHeroFilterAsc()
                    },
                )
            ,
        ){
            Checkbox(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .testTag(TAG_HERO_FILTER_ASC)
                    .align(Alignment.CenterVertically)
                ,
                enabled= isEnabled,
                checked = isEnabled && isAscSelected,
                onCheckedChange = {
                    onUpdateHeroFilterAsc()
                },
                colors = CheckboxDefaults.colors(MaterialTheme.colorScheme.primary)
            )
            Text(
                text = ascString,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}