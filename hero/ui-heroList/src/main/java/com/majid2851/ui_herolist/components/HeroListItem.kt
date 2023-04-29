package com.majid2851.ui_herolist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import com.majid2851.hero_domain.Hero
import com.majid2851.ui_herolist.ui.test.TAG_HERO_NAME
import com.majid2851.ui_herolist.ui.test.TAG_HERO_PRIMARY_ATTRIBUTE
import kotlin.math.round
import com.majid2851.ui_herolist.R


@Composable
fun HeroListItem(
    hero: Hero,
    onSelectHero: (Int) -> Unit,
    imageLoader: ImageLoader,
){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .background(MaterialTheme.colorScheme.background)
            .clickable {
                onSelectHero(hero.id)
            }
        ,
        tonalElevation = 8.dp
        , shadowElevation = 8.dp
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
            ,
            verticalAlignment = Alignment.CenterVertically
        ){
            val painter= rememberImagePainter(
                hero.img,
                imageLoader=imageLoader,
                builder = {
                    placeholder(if (isSystemInDarkTheme())
                        R.drawable.black_background
                    else R.drawable.white_background)
                }
            )
            Image( // TODO(Replace with Image)
                modifier = Modifier
                    .width(120.dp)
                    .height(70.dp)
                    .background(Color.LightGray)
                , painter = painter
                , contentDescription = hero.localizedName
                , contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(.8f) // fill 80% of remaining width
                    .padding(start = 12.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .testTag(TAG_HERO_NAME)
                    ,
                    text = hero.localizedName,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier
                        .testTag(TAG_HERO_PRIMARY_ATTRIBUTE)
                    ,
                    text = hero.primaryAttribute.uiValue,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth() // Fill the rest of the width (100% - 80% = 20%)
                    .padding(end = 12.dp)
                ,
                horizontalAlignment = Alignment.End
            ) {
                // Using remember in list item does not behave correctly?
//                val proWR: Int = remember{round(hero.proWins.toDouble() / hero.proPick.toDouble() * 100).toInt()}
                val proWR: Int = round(hero.proWins.toDouble() / hero.proPick.toDouble() * 100).toInt()
                Text(
                    text = "${proWR}%",
                    style = MaterialTheme.typography.titleMedium,
                    color = if(proWR > 50) Color(0xFF009a34) else MaterialTheme.colorScheme.error,
                )
            }
        }
    }
}