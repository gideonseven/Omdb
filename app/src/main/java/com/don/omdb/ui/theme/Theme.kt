/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.don.omdb.ui.theme.backgroundDark
import com.don.omdb.ui.theme.backgroundDarkHighContrast
import com.don.omdb.ui.theme.backgroundDarkMediumContrast
import com.don.omdb.ui.theme.backgroundLight
import com.don.omdb.ui.theme.backgroundLightHighContrast
import com.don.omdb.ui.theme.backgroundLightMediumContrast
import com.don.omdb.ui.theme.errorContainerDark
import com.don.omdb.ui.theme.errorContainerDarkHighContrast
import com.don.omdb.ui.theme.errorContainerDarkMediumContrast
import com.don.omdb.ui.theme.errorContainerLight
import com.don.omdb.ui.theme.errorContainerLightHighContrast
import com.don.omdb.ui.theme.errorContainerLightMediumContrast
import com.don.omdb.ui.theme.errorDark
import com.don.omdb.ui.theme.errorDarkHighContrast
import com.don.omdb.ui.theme.errorDarkMediumContrast
import com.don.omdb.ui.theme.errorLight
import com.don.omdb.ui.theme.errorLightHighContrast
import com.don.omdb.ui.theme.errorLightMediumContrast
import com.don.omdb.ui.theme.inverseOnSurfaceDark
import com.don.omdb.ui.theme.inverseOnSurfaceDarkHighContrast
import com.don.omdb.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.don.omdb.ui.theme.inverseOnSurfaceLight
import com.don.omdb.ui.theme.inverseOnSurfaceLightHighContrast
import com.don.omdb.ui.theme.inverseOnSurfaceLightMediumContrast
import com.don.omdb.ui.theme.inversePrimaryDark
import com.don.omdb.ui.theme.inversePrimaryDarkHighContrast
import com.don.omdb.ui.theme.inversePrimaryDarkMediumContrast
import com.don.omdb.ui.theme.inversePrimaryLight
import com.don.omdb.ui.theme.inversePrimaryLightHighContrast
import com.don.omdb.ui.theme.inversePrimaryLightMediumContrast
import com.don.omdb.ui.theme.inverseSurfaceDark
import com.don.omdb.ui.theme.inverseSurfaceDarkHighContrast
import com.don.omdb.ui.theme.inverseSurfaceDarkMediumContrast
import com.don.omdb.ui.theme.inverseSurfaceLight
import com.don.omdb.ui.theme.inverseSurfaceLightHighContrast
import com.don.omdb.ui.theme.inverseSurfaceLightMediumContrast
import com.don.omdb.ui.theme.onBackgroundDark
import com.don.omdb.ui.theme.onBackgroundDarkHighContrast
import com.don.omdb.ui.theme.onBackgroundDarkMediumContrast
import com.don.omdb.ui.theme.onBackgroundLight
import com.don.omdb.ui.theme.onBackgroundLightHighContrast
import com.don.omdb.ui.theme.onBackgroundLightMediumContrast
import com.don.omdb.ui.theme.onErrorContainerDark
import com.don.omdb.ui.theme.onErrorContainerDarkHighContrast
import com.don.omdb.ui.theme.onErrorContainerDarkMediumContrast
import com.don.omdb.ui.theme.onErrorContainerLight
import com.don.omdb.ui.theme.onErrorContainerLightHighContrast
import com.don.omdb.ui.theme.onErrorContainerLightMediumContrast
import com.don.omdb.ui.theme.onErrorDark
import com.don.omdb.ui.theme.onErrorDarkHighContrast
import com.don.omdb.ui.theme.onErrorDarkMediumContrast
import com.don.omdb.ui.theme.onErrorLight
import com.don.omdb.ui.theme.onErrorLightHighContrast
import com.don.omdb.ui.theme.onErrorLightMediumContrast
import com.don.omdb.ui.theme.onPrimaryContainerDark
import com.don.omdb.ui.theme.onPrimaryContainerDarkHighContrast
import com.don.omdb.ui.theme.onPrimaryContainerDarkMediumContrast
import com.don.omdb.ui.theme.onPrimaryContainerLight
import com.don.omdb.ui.theme.onPrimaryContainerLightHighContrast
import com.don.omdb.ui.theme.onPrimaryContainerLightMediumContrast
import com.don.omdb.ui.theme.onPrimaryDark
import com.don.omdb.ui.theme.onPrimaryDarkHighContrast
import com.don.omdb.ui.theme.onPrimaryDarkMediumContrast
import com.don.omdb.ui.theme.onPrimaryLight
import com.don.omdb.ui.theme.onPrimaryLightHighContrast
import com.don.omdb.ui.theme.onPrimaryLightMediumContrast
import com.don.omdb.ui.theme.onSecondaryContainerDark
import com.don.omdb.ui.theme.onSecondaryContainerDarkHighContrast
import com.don.omdb.ui.theme.onSecondaryContainerDarkMediumContrast
import com.don.omdb.ui.theme.onSecondaryContainerLight
import com.don.omdb.ui.theme.onSecondaryContainerLightHighContrast
import com.don.omdb.ui.theme.onSecondaryContainerLightMediumContrast
import com.don.omdb.ui.theme.onSecondaryDark
import com.don.omdb.ui.theme.onSecondaryDarkHighContrast
import com.don.omdb.ui.theme.onSecondaryDarkMediumContrast
import com.don.omdb.ui.theme.onSecondaryLight
import com.don.omdb.ui.theme.onSecondaryLightHighContrast
import com.don.omdb.ui.theme.onSecondaryLightMediumContrast
import com.don.omdb.ui.theme.onSurfaceDark
import com.don.omdb.ui.theme.onSurfaceDarkHighContrast
import com.don.omdb.ui.theme.onSurfaceDarkMediumContrast
import com.don.omdb.ui.theme.onSurfaceLight
import com.don.omdb.ui.theme.onSurfaceLightHighContrast
import com.don.omdb.ui.theme.onSurfaceLightMediumContrast
import com.don.omdb.ui.theme.onSurfaceVariantDark
import com.don.omdb.ui.theme.onSurfaceVariantDarkHighContrast
import com.don.omdb.ui.theme.onSurfaceVariantDarkMediumContrast
import com.don.omdb.ui.theme.onSurfaceVariantLight
import com.don.omdb.ui.theme.onSurfaceVariantLightHighContrast
import com.don.omdb.ui.theme.onSurfaceVariantLightMediumContrast
import com.don.omdb.ui.theme.onTertiaryContainerDark
import com.don.omdb.ui.theme.onTertiaryContainerDarkHighContrast
import com.don.omdb.ui.theme.onTertiaryContainerDarkMediumContrast
import com.don.omdb.ui.theme.onTertiaryContainerLight
import com.don.omdb.ui.theme.onTertiaryContainerLightHighContrast
import com.don.omdb.ui.theme.onTertiaryContainerLightMediumContrast
import com.don.omdb.ui.theme.onTertiaryDark
import com.don.omdb.ui.theme.onTertiaryDarkHighContrast
import com.don.omdb.ui.theme.onTertiaryDarkMediumContrast
import com.don.omdb.ui.theme.onTertiaryLight
import com.don.omdb.ui.theme.onTertiaryLightHighContrast
import com.don.omdb.ui.theme.onTertiaryLightMediumContrast
import com.don.omdb.ui.theme.outlineDark
import com.don.omdb.ui.theme.outlineDarkHighContrast
import com.don.omdb.ui.theme.outlineDarkMediumContrast
import com.don.omdb.ui.theme.outlineLight
import com.don.omdb.ui.theme.outlineLightHighContrast
import com.don.omdb.ui.theme.outlineLightMediumContrast
import com.don.omdb.ui.theme.outlineVariantDark
import com.don.omdb.ui.theme.outlineVariantDarkHighContrast
import com.don.omdb.ui.theme.outlineVariantDarkMediumContrast
import com.don.omdb.ui.theme.outlineVariantLight
import com.don.omdb.ui.theme.outlineVariantLightHighContrast
import com.don.omdb.ui.theme.outlineVariantLightMediumContrast
import com.don.omdb.ui.theme.primaryContainerDark
import com.don.omdb.ui.theme.primaryContainerDarkHighContrast
import com.don.omdb.ui.theme.primaryContainerDarkMediumContrast
import com.don.omdb.ui.theme.primaryContainerLight
import com.don.omdb.ui.theme.primaryContainerLightHighContrast
import com.don.omdb.ui.theme.primaryContainerLightMediumContrast
import com.don.omdb.ui.theme.primaryDark
import com.don.omdb.ui.theme.primaryDarkHighContrast
import com.don.omdb.ui.theme.primaryDarkMediumContrast
import com.don.omdb.ui.theme.primaryLight
import com.don.omdb.ui.theme.primaryLightHighContrast
import com.don.omdb.ui.theme.primaryLightMediumContrast
import com.don.omdb.ui.theme.scrimDark
import com.don.omdb.ui.theme.scrimDarkHighContrast
import com.don.omdb.ui.theme.scrimDarkMediumContrast
import com.don.omdb.ui.theme.scrimLight
import com.don.omdb.ui.theme.scrimLightHighContrast
import com.don.omdb.ui.theme.scrimLightMediumContrast
import com.don.omdb.ui.theme.secondaryContainerDark
import com.don.omdb.ui.theme.secondaryContainerDarkHighContrast
import com.don.omdb.ui.theme.secondaryContainerDarkMediumContrast
import com.don.omdb.ui.theme.secondaryContainerLight
import com.don.omdb.ui.theme.secondaryContainerLightHighContrast
import com.don.omdb.ui.theme.secondaryContainerLightMediumContrast
import com.don.omdb.ui.theme.secondaryDark
import com.don.omdb.ui.theme.secondaryDarkHighContrast
import com.don.omdb.ui.theme.secondaryDarkMediumContrast
import com.don.omdb.ui.theme.secondaryLight
import com.don.omdb.ui.theme.secondaryLightHighContrast
import com.don.omdb.ui.theme.secondaryLightMediumContrast
import com.don.omdb.ui.theme.surfaceBrightDark
import com.don.omdb.ui.theme.surfaceBrightDarkHighContrast
import com.don.omdb.ui.theme.surfaceBrightDarkMediumContrast
import com.don.omdb.ui.theme.surfaceBrightLight
import com.don.omdb.ui.theme.surfaceBrightLightHighContrast
import com.don.omdb.ui.theme.surfaceBrightLightMediumContrast
import com.don.omdb.ui.theme.surfaceContainerDark
import com.don.omdb.ui.theme.surfaceContainerDarkHighContrast
import com.don.omdb.ui.theme.surfaceContainerDarkMediumContrast
import com.don.omdb.ui.theme.surfaceContainerHighDark
import com.don.omdb.ui.theme.surfaceContainerHighDarkHighContrast
import com.don.omdb.ui.theme.surfaceContainerHighDarkMediumContrast
import com.don.omdb.ui.theme.surfaceContainerHighLight
import com.don.omdb.ui.theme.surfaceContainerHighLightHighContrast
import com.don.omdb.ui.theme.surfaceContainerHighLightMediumContrast
import com.don.omdb.ui.theme.surfaceContainerHighestDark
import com.don.omdb.ui.theme.surfaceContainerHighestDarkHighContrast
import com.don.omdb.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.don.omdb.ui.theme.surfaceContainerHighestLight
import com.don.omdb.ui.theme.surfaceContainerHighestLightHighContrast
import com.don.omdb.ui.theme.surfaceContainerHighestLightMediumContrast
import com.don.omdb.ui.theme.surfaceContainerLight
import com.don.omdb.ui.theme.surfaceContainerLightHighContrast
import com.don.omdb.ui.theme.surfaceContainerLightMediumContrast
import com.don.omdb.ui.theme.surfaceContainerLowDark
import com.don.omdb.ui.theme.surfaceContainerLowDarkHighContrast
import com.don.omdb.ui.theme.surfaceContainerLowDarkMediumContrast
import com.don.omdb.ui.theme.surfaceContainerLowLight
import com.don.omdb.ui.theme.surfaceContainerLowLightHighContrast
import com.don.omdb.ui.theme.surfaceContainerLowLightMediumContrast
import com.don.omdb.ui.theme.surfaceContainerLowestDark
import com.don.omdb.ui.theme.surfaceContainerLowestDarkHighContrast
import com.don.omdb.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.don.omdb.ui.theme.surfaceContainerLowestLight
import com.don.omdb.ui.theme.surfaceContainerLowestLightHighContrast
import com.don.omdb.ui.theme.surfaceContainerLowestLightMediumContrast
import com.don.omdb.ui.theme.surfaceDark
import com.don.omdb.ui.theme.surfaceDarkHighContrast
import com.don.omdb.ui.theme.surfaceDarkMediumContrast
import com.don.omdb.ui.theme.surfaceDimDark
import com.don.omdb.ui.theme.surfaceDimDarkHighContrast
import com.don.omdb.ui.theme.surfaceDimDarkMediumContrast
import com.don.omdb.ui.theme.surfaceDimLight
import com.don.omdb.ui.theme.surfaceDimLightHighContrast
import com.don.omdb.ui.theme.surfaceDimLightMediumContrast
import com.don.omdb.ui.theme.surfaceLight
import com.don.omdb.ui.theme.surfaceLightHighContrast
import com.don.omdb.ui.theme.surfaceLightMediumContrast
import com.don.omdb.ui.theme.surfaceVariantDark
import com.don.omdb.ui.theme.surfaceVariantDarkHighContrast
import com.don.omdb.ui.theme.surfaceVariantDarkMediumContrast
import com.don.omdb.ui.theme.surfaceVariantLight
import com.don.omdb.ui.theme.surfaceVariantLightHighContrast
import com.don.omdb.ui.theme.surfaceVariantLightMediumContrast
import com.don.omdb.ui.theme.tertiaryContainerDark
import com.don.omdb.ui.theme.tertiaryContainerDarkHighContrast
import com.don.omdb.ui.theme.tertiaryContainerDarkMediumContrast
import com.don.omdb.ui.theme.tertiaryContainerLight
import com.don.omdb.ui.theme.tertiaryContainerLightHighContrast
import com.don.omdb.ui.theme.tertiaryContainerLightMediumContrast
import com.don.omdb.ui.theme.tertiaryDark
import com.don.omdb.ui.theme.tertiaryDarkHighContrast
import com.don.omdb.ui.theme.tertiaryDarkMediumContrast
import com.don.omdb.ui.theme.tertiaryLight
import com.don.omdb.ui.theme.tertiaryLightHighContrast
import com.don.omdb.ui.theme.tertiaryLightMediumContrast

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
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
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
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
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
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
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
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
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
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
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
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
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
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
