plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.fiap.lara"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.fiap.lara"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation("androidx.activity:activity-compose:1.13.0")
    implementation("androidx.core:core-ktx:1.18.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.4")
    implementation("androidx.compose.ui:ui:1.7.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.2")
    implementation("androidx.compose.foundation:foundation:1.7.2")
    implementation("androidx.compose.material3:material3-android:1.4.0")
    implementation("androidx.compose.material:material-icons-extended:1.7.0")
    implementation("androidx.navigation:navigation-compose:2.9.8")
    debugImplementation("androidx.compose.ui:ui-tooling:1.7.2")
}
