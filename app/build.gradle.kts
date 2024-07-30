plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.cloffygames.webviewexample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cloffygames.webviewexample"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core KTX, Android API'lerini Kotlin diline özgü uzantılarla daha kolay kullanmayı sağlar.
    implementation(libs.androidx.core.ktx)

    // Lifecycle-runtime KTX, Android uygulamalarında yaşam döngüsü yönetimini kolaylaştırır.
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Activity Compose, Jetpack Compose ile etkinlik yönetimi için gerekli kütüphanedir.
    implementation(libs.androidx.activity.compose)

    // Compose BOM (Bill of Materials), Compose ile ilgili bağımlılıkların uyumlu sürümlerini kullanmayı sağlar.
    implementation(platform(libs.androidx.compose.bom))

    // Jetpack Compose'un temel UI bileşenleri.
    implementation(libs.androidx.ui)

    // Jetpack Compose'un grafiksel araçları.
    implementation(libs.androidx.ui.graphics)

    // Jetpack Compose'da araç gereç (tooling) önizlemeleri sağlar.
    implementation(libs.androidx.ui.tooling.preview)

    // Material Design 3 kütüphanesi, Compose için.
    implementation(libs.androidx.material3)

    // JUnit, birim testleri için kullanılan popüler bir test kütüphanesi.
    testImplementation(libs.junit)

    // AndroidX JUnit, Android testlerinde JUnit desteği sağlar.
    androidTestImplementation(libs.androidx.junit)

    // Espresso, Android kullanıcı arayüzü testleri için kullanılan bir kütüphanedir.
    androidTestImplementation(libs.androidx.espresso.core)

    // Compose BOM (Bill of Materials), Compose ile ilgili bağımlılıkların uyumlu sürümlerini kullanmayı sağlar.
    androidTestImplementation(platform(libs.androidx.compose.bom))

    // Jetpack Compose'da JUnit 4 ile UI testlerini yapmayı sağlar.
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Compose araç gereçlerini (tooling) hata ayıklama modunda kullanmayı sağlar.
    debugImplementation(libs.androidx.ui.tooling)

    // Jetpack Compose'da test manifest dosyalarını yönetmeyi sağlar.
    debugImplementation(libs.androidx.ui.test.manifest)

}
