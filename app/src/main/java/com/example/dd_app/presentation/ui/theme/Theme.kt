package com.example.dd_app.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Colors.Purple,
    secondary = Colors.Gray,
    tertiary = Colors.Dark,
    error = Colors.Error,
    background = Colors.White,
    surface = Colors.Surface,
    outline = Colors.Unfocused,
    surfaceDim = Colors.FilledComment
)

private val LightColorScheme = lightColorScheme(
    primary = Colors.Purple,
    secondary = Colors.Gray,
    tertiary = Colors.Dark,
    error = Colors.Error,
    background = Colors.White,
    surface = Colors.Dark,
    outline = Colors.Unfocused,
    surfaceDim = Colors.FilledComment
)

@Composable
fun Dd_appTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
    )
}