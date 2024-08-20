package com.fappslab.mbchallenge.libraries.design.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.fappslab.mbchallenge.libraries.design.theme.defaults.LocalPlutoColorsDark
import com.fappslab.mbchallenge.libraries.design.theme.defaults.LocalPlutoColorsLight
import com.fappslab.mbchallenge.libraries.design.theme.defaults.LocalPlutoDimens
import com.fappslab.mbchallenge.libraries.design.theme.defaults.LocalPlutoElevation
import com.fappslab.mbchallenge.libraries.design.theme.defaults.LocalPlutoFontFontStyle
import com.fappslab.mbchallenge.libraries.design.theme.defaults.LocalPlutoFontSizing
import com.fappslab.mbchallenge.libraries.design.theme.defaults.LocalPlutoOpacity
import com.fappslab.mbchallenge.libraries.design.theme.defaults.LocalPlutoRadius
import com.fappslab.mbchallenge.libraries.design.theme.defaults.LocalPlutoStroke
import com.fappslab.mbchallenge.libraries.design.theme.defaults.LocalPlutoTextDark
import com.fappslab.mbchallenge.libraries.design.theme.defaults.LocalPlutoTextLight
import com.fappslab.mbchallenge.libraries.design.theme.defaults.LocalPlutoTypography
import com.fappslab.mbchallenge.libraries.design.theme.defaults.dark.DesignLanguageDark
import com.fappslab.mbchallenge.libraries.design.theme.defaults.dark.PlutoColorsDark
import com.fappslab.mbchallenge.libraries.design.theme.defaults.light.DesignLanguageLight
import com.fappslab.mbchallenge.libraries.design.theme.defaults.light.PlutoColorsLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.PlutoColors
import com.fappslab.mbchallenge.libraries.design.theme.tokens.PlutoDimens
import com.fappslab.mbchallenge.libraries.design.theme.tokens.PlutoElevation
import com.fappslab.mbchallenge.libraries.design.theme.tokens.PlutoFontSizing
import com.fappslab.mbchallenge.libraries.design.theme.tokens.PlutoFontStyle
import com.fappslab.mbchallenge.libraries.design.theme.tokens.PlutoOpacity
import com.fappslab.mbchallenge.libraries.design.theme.tokens.PlutoRadius
import com.fappslab.mbchallenge.libraries.design.theme.tokens.PlutoStroke
import com.fappslab.mbchallenge.libraries.design.theme.tokens.PlutoText
import com.fappslab.mbchallenge.libraries.design.theme.tokens.PlutoTypography
import com.fappslab.mbchallenge.libraries.design.theme.tokens.backgroundDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.backgroundDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.backgroundDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.backgroundLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.backgroundLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.backgroundLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.errorContainerDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.errorContainerDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.errorContainerDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.errorContainerLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.errorContainerLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.errorContainerLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.errorDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.errorDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.errorDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.errorLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.errorLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.errorLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.inverseOnSurfaceDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.inverseOnSurfaceDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.inverseOnSurfaceDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.inverseOnSurfaceLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.inverseOnSurfaceLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.inverseOnSurfaceLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.inversePrimaryDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.inversePrimaryDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.inversePrimaryDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.inversePrimaryLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.inversePrimaryLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.inversePrimaryLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.inverseSurfaceDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.inverseSurfaceDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.inverseSurfaceDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.inverseSurfaceLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.inverseSurfaceLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.inverseSurfaceLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onBackgroundDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onBackgroundDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onBackgroundDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onBackgroundLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onBackgroundLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onBackgroundLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onErrorContainerDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onErrorContainerDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onErrorContainerDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onErrorContainerLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onErrorContainerLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onErrorContainerLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onErrorDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onErrorDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onErrorDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onErrorLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onErrorLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onErrorLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onPrimaryContainerDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onPrimaryContainerDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onPrimaryContainerDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onPrimaryContainerLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onPrimaryContainerLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onPrimaryContainerLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onPrimaryDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onPrimaryDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onPrimaryDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onPrimaryLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onPrimaryLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onPrimaryLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSecondaryContainerDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSecondaryContainerDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSecondaryContainerDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSecondaryContainerLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSecondaryContainerLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSecondaryContainerLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSecondaryDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSecondaryDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSecondaryDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSecondaryLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSecondaryLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSecondaryLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSurfaceDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSurfaceDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSurfaceDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSurfaceLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSurfaceLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSurfaceLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSurfaceVariantDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSurfaceVariantDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSurfaceVariantDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSurfaceVariantLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSurfaceVariantLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onSurfaceVariantLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onTertiaryContainerDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onTertiaryContainerDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onTertiaryContainerDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onTertiaryContainerLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onTertiaryContainerLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onTertiaryContainerLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onTertiaryDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onTertiaryDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onTertiaryDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onTertiaryLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onTertiaryLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.onTertiaryLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.outlineDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.outlineDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.outlineDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.outlineLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.outlineLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.outlineLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.outlineVariantDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.outlineVariantDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.outlineVariantDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.outlineVariantLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.outlineVariantLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.outlineVariantLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.primaryContainerDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.primaryContainerDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.primaryContainerDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.primaryContainerLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.primaryContainerLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.primaryContainerLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.primaryDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.primaryDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.primaryDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.primaryLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.primaryLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.primaryLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.scrimDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.scrimDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.scrimDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.scrimLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.scrimLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.scrimLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.secondaryContainerDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.secondaryContainerDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.secondaryContainerDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.secondaryContainerLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.secondaryContainerLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.secondaryContainerLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.secondaryDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.secondaryDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.secondaryDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.secondaryLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.secondaryLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.secondaryLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.surfaceDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.surfaceDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.surfaceDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.surfaceLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.surfaceLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.surfaceLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.surfaceVariantDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.surfaceVariantDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.surfaceVariantDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.surfaceVariantLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.surfaceVariantLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.surfaceVariantLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.tertiaryContainerDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.tertiaryContainerDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.tertiaryContainerDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.tertiaryContainerLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.tertiaryContainerLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.tertiaryContainerLightMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.tertiaryDark
import com.fappslab.mbchallenge.libraries.design.theme.tokens.tertiaryDarkHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.tertiaryDarkMediumContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.tertiaryLight
import com.fappslab.mbchallenge.libraries.design.theme.tokens.tertiaryLightHighContrast
import com.fappslab.mbchallenge.libraries.design.theme.tokens.tertiaryLightMediumContrast

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun PlutoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    statusBarColor: Color = colorScheme.background.copy(PlutoOpacity.opaque),
    navigationBarColor: Color = colorScheme.background.copy(PlutoOpacity.opaque),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) {
                dynamicDarkColorScheme(context).copy(
                    primary = primaryDark,
                    secondary = secondaryDark,
                    tertiary = tertiaryDark
                )
            } else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = statusBarColor.toArgb()
            window.navigationBarColor = navigationBarColor.toArgb()
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
                //hide(WindowInsetsCompat.Type.statusBars())
                //hide(WindowInsetsCompat.Type.navigationBars())
            }
        }
    }
    CompositionLocalProvider(
        LocalPlutoDimens provides PlutoDimens,
        LocalPlutoElevation provides PlutoElevation,
        LocalPlutoFontSizing provides PlutoFontSizing,
        LocalPlutoFontFontStyle provides PlutoFontStyle,
        LocalPlutoOpacity provides PlutoOpacity,
        LocalPlutoRadius provides PlutoRadius,
        LocalPlutoStroke provides PlutoStroke,
        LocalPlutoTypography provides PlutoTypography,
        LocalPlutoTextDark provides DesignLanguageDark.text,
        LocalPlutoTextLight provides DesignLanguageLight.text,
        LocalPlutoColorsLight provides PlutoColorsLight,
        LocalPlutoColorsDark provides PlutoColorsDark,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography(
                displayLarge = PlutoTypography.displayLarge,
                displayMedium = PlutoTypography.displayMedium,
                displaySmall = PlutoTypography.displaySmall,
                headlineLarge = PlutoTypography.headlineLarge,
                headlineMedium = PlutoTypography.headlineMedium,
                headlineSmall = PlutoTypography.headlineSmall,
                titleLarge = PlutoTypography.titleLarge,
                titleMedium = PlutoTypography.titleMedium,
                titleSmall = PlutoTypography.titleSmall,
                bodyLarge = PlutoTypography.bodyLarge,
                bodyMedium = PlutoTypography.bodyMedium,
                bodySmall = PlutoTypography.bodySmall,
                labelLarge = PlutoTypography.labelLarge,
                labelMedium = PlutoTypography.labelMedium,
                labelSmall = PlutoTypography.labelSmall
            ),
            content = content
        )
    }
}

object PlutoTheme {
    val dimen: PlutoDimens
        @Composable
        @ReadOnlyComposable
        get() = LocalPlutoDimens.current

    val elevation: PlutoElevation
        @Composable
        @ReadOnlyComposable
        get() = LocalPlutoElevation.current

    val fontSizing: PlutoFontSizing
        @Composable
        @ReadOnlyComposable
        get() = LocalPlutoFontSizing.current

    val fontStyle: PlutoFontStyle
        @Composable
        @ReadOnlyComposable
        get() = LocalPlutoFontFontStyle.current

    val opacity: PlutoOpacity
        @Composable
        @ReadOnlyComposable
        get() = LocalPlutoOpacity.current

    val radius: PlutoRadius
        @Composable
        @ReadOnlyComposable
        get() = LocalPlutoRadius.current

    val stroke: PlutoStroke
        @Composable
        @ReadOnlyComposable
        get() = LocalPlutoStroke.current

    val text: PlutoText
        @Composable
        @ReadOnlyComposable
        get() = if (isSystemInDarkTheme()) {
            LocalPlutoTextDark.current
        } else {
            LocalPlutoTextLight.current
        }

    val typography: PlutoTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalPlutoTypography.current

    val colors: PlutoColors
        @Composable
        @ReadOnlyComposable
        get() = if (isSystemInDarkTheme()) {
            LocalPlutoColorsDark.current
        } else {
            LocalPlutoColorsLight.current
        }
}
