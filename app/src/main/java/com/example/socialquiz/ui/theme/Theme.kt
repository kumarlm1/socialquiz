package com.example.socialquiz.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.White,
    primaryVariant = Color.Black,
    secondary = Teal200,
    background = Color.Black,
    surface = Color.Black,
    onSecondary = Color(105, 91, 225),
)

private val LightColorPalette = lightColors(
    primary = Color.Black,
    primaryVariant = Purple700,
    secondary = Teal200,
    onSecondary = Color(105, 91, 225),

    background = Color.White,
    surface = Color.White,
    onPrimary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

class Themee{
    companion object{
        var isDark  = mutableStateOf(false)
    }
}

@Composable
fun SocialQuizTheme(darkTheme: Boolean = Themee.isDark.value, content: @Composable () -> Unit) {
    val colors = if (!darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}