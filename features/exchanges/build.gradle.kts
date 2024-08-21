plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}
apply("$rootDir/plugins/android-build.gradle")

android {
    namespace = "${Config.NAMESPACE}.features.exchanges"
}

dependencies {
    // Modules
    implementation(project(Modules.arch))
    implementation(project(Modules.domain))

    // Libs
}
