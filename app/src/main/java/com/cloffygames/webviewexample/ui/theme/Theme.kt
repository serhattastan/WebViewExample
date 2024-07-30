package com.cloffygames.webviewexample.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

// Karanlık tema için renk şemasını tanımlar
private val DarkColorScheme = darkColorScheme(
    primary = Purple80, // Birincil renk
    secondary = PurpleGrey80, // İkincil renk
    tertiary = Pink80 // Üçüncül renk
)

// Aydınlık tema için renk şemasını tanımlar
private val LightColorScheme = lightColorScheme(
    primary = Purple40, // Birincil renk
    secondary = PurpleGrey40, // İkincil renk
    tertiary = Pink40 // Üçüncül renk
)

// WebViewExampleTheme, uygulamanın genel tema ayarlarını yönetir
@Composable
fun WebViewExampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Sistem karanlık modda mı kontrol eder
    dynamicColor: Boolean = true, // Dinamik renk desteğini etkinleştirir
    content: @Composable () -> Unit // İçerik bileşeni
) {
    // Uygulamanın renk şemasını belirler
    val colorScheme = when {
        // Android S ve üzeri için dinamik renk desteğini kullanır
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        // Karanlık tema aktifse karanlık renk şemasını kullanır
        darkTheme -> DarkColorScheme
        // Aksi takdirde aydınlık renk şemasını kullanır
        else -> LightColorScheme
    }

    // Geçerli görünümü alır
    val view = LocalView.current
    if (!view.isInEditMode) {
        // Yan etkileri yönetir, burada durum çubuğunun rengini ayarlar
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb() // Durum çubuğu rengini ayarlar
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme // Durum çubuğu simgelerinin rengini ayarlar
        }
    }

    // MaterialTheme ile temayı uygular
    MaterialTheme(
        colorScheme = colorScheme, // Renk şemasını uygular
        typography = Typography, // Tipografi ayarlarını uygular
        content = content // İçeriği uygular
    )
}
