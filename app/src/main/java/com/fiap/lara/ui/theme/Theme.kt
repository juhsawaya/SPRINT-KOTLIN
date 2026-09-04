package com.fiap.lara.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorScheme = lightColorScheme(
    primary = LaraNavy,
    onPrimary = Color.White,
    secondary = LaraRose,
    onSecondary = Color.White,
    tertiary = LaraPink,
    background = LaraPaper,
    onBackground = LaraInk,
    surface = Color.White,
    onSurface = LaraInk,
    surfaceVariant = LaraBlush,
    onSurfaceVariant = LaraSlate,
    outline = Color(0xFFE4CCD4)
)

@Composable
fun LaraTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = AppTypography,
        content = content
    )
}
