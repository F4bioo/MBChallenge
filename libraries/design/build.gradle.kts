plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}
apply("$rootDir/plugins/android-build.gradle")

android {
    namespace = "${Config.NAMESPACE}.libraries.design"

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Modules

    // Libs
    implementation(libs.androidx.ui.text.google.fonts)
    implementation (libs.core.splashscreen)
    api(libs.coil.compose)
    api(libs.lottie.compose)
}
