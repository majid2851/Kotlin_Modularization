package com.majid2851.kotlin_modularization.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Blue300,
    primaryContainer = Blue700,
    onPrimary = Color.White,
    secondary = Color.Black,
    secondaryContainer = Teal300,
    onSecondary = Color.White,
    error = RedErrorLight,
    onError = RedErrorDark,
    background = Color.Black,
    onBackground = Color.White,
    surface = Black1,
    onSurface = Color.White,
)

private val LightColorPalette = lightColorScheme(
    primary = Blue600,
    primaryContainer = Blue400,
    onPrimary = Black1,
    secondary = Color.White,
    secondaryContainer = Teal300,
    onSecondary = Color.Black,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = Grey1,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Black1,
)

@Composable
fun DotaInfoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if(darkTheme){
        DarkColorPalette
    } else{
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = QuickSandTypography,
        shapes = AppShapes,
        content = content
    )
}