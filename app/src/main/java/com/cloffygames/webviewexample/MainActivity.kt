package com.cloffygames.webviewexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cloffygames.webviewexample.ui.theme.WebViewExampleTheme
import androidx.compose.ui.viewinterop.AndroidView
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.ui.unit.dp

// MainActivity, uygulamanın ana etkinliğini temsil eder ve bir bileşen etkinliği (ComponentActivity) olarak tanımlanır.
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContent, Compose UI'yi etkinliğin içerik görünümü olarak ayarlamak için kullanılır.
        setContent {
            WebViewExampleTheme {
                // Scaffold, temel ekran yapısını oluşturur ve çeşitli UI bileşenleri için yuvalar sağlar.
                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                    // Üst çubuk (TopAppBar) bileşenini tanımlar.
                    topBar = {
                        TopAppBar(
                            title = { Text("WebView Example") }, // Üst çubuk başlığını ayarlar.
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary, // Üst çubuğun arka plan rengini ayarlar.
                                titleContentColor = MaterialTheme.colorScheme.onPrimary // Başlık metninin rengini ayarlar.
                            )
                        )
                    }
                ) { innerPadding ->
                    // currentScreen, mevcut ekranı belirten bir değişken.
                    var currentScreen by remember { mutableStateOf("home") }

                    // Ekran içeriğini belirler.
                    if (currentScreen == "home") {
                        HomeScreen(
                            onButtonClick = { currentScreen = "webview" }, // Butona tıklanınca currentScreen'i günceller.
                            modifier = Modifier.padding(innerPadding) // İçerik dolgusunu ayarlar.
                        )
                    } else {
                        WebViewScreen(
                            url = "https://github.com/serhattastan", // WebView içinde yüklenecek URL'yi belirtir.
                            modifier = Modifier.padding(innerPadding) // İçerik dolgusunu ayarlar.
                        )
                    }
                }
            }
        }
    }
}

// HomeScreen, ana ekranı tanımlar ve bir buton içerir.
@Composable
fun HomeScreen(onButtonClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Welcome to the WebView Example!",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = onButtonClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Go to WebView")
        }
    }
}

// WebViewScreen, belirtilen URL'yi yüklemek için bir WebView bileşeni oluşturur.
@Composable
fun WebViewScreen(url: String, modifier: Modifier = Modifier) {
    // AndroidView, geleneksel Android bileşenlerini (burada WebView) Compose ile kullanmak için kullanılır.
    AndroidView(
        factory = {
            WebView(it).apply {
                webViewClient = WebViewClient() // WebViewClient, sayfa yükleme işlemlerini yönetir.
                settings.javaScriptEnabled = true // JavaScript desteğini etkinleştirir.
                loadUrl(url) // Belirtilen URL'yi yükler.
            }
        },
        modifier = modifier.fillMaxSize() // WebView'in ekranı doldurmasını sağlar.
    )
}

// WebViewScreen'in önizlemesini sağlayan fonksiyon.
@Preview(showBackground = true)
@Composable
fun WebViewScreenPreview() {
    WebViewExampleTheme {
        WebViewScreen("https://www.example.com") // Önizleme için örnek bir URL yükler.
    }
}

// HomeScreen'in önizlemesini sağlayan fonksiyon.
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    WebViewExampleTheme {
        HomeScreen(onButtonClick = {})
    }
}
