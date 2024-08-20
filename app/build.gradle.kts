import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}
apply("$rootDir/plugins/android-build.gradle")

val keystorePropertiesFile = rootProject.file("secrets/keystore.properties")

android {
    namespace = Config.NAMESPACE

    defaultConfig {
        applicationId = Config.APPLICATION_ID
        versionCode = Config.VERSION_CODE
        versionName = Config.VERSION_NAME
    }
    signingConfigs {
        if (keystorePropertiesFile.exists()) {
            val keystoreProperties = Properties()
            keystoreProperties.load(FileInputStream(keystorePropertiesFile))

            create("signing") {
                storeFile = file(keystoreProperties["STORE_FILE"] as String)
                storePassword = keystoreProperties["STORE_PASSWORD"] as String
                keyPassword = keystoreProperties["KEY_PASSWORD"] as String
                keyAlias = keystoreProperties["KEY_ALIAS"] as String
            }
        }
    }

    buildTypes {
        named("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
        named("staging") {
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
            matchingFallbacks += listOf("release", "debug")
            isMinifyEnabled = true
        }
        named("release") {
            isMinifyEnabled = true
            isShrinkResources = true
        }
        configureEach {
            if (name == "staging" || name == "release") {
                if (keystorePropertiesFile.exists()) {
                    signingConfig = signingConfigs.getByName("signing")
                } else {
                    println("keystore.properties not found for $name variant, skipping signing config")
                }
            }
        }
    }
    sourceSets {
        getByName("main").java.srcDirs("src/main/java")
        getByName("debug").java.srcDirs("src/debug/java")
        getByName("release").java.srcDirs("src/release/java")
        getByName("staging").java.srcDirs("src/staging/java")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    // Modules
    implementation(project(Modules.arch))
    implementation(project(Modules.design))
    implementation(project(Modules.navigation))

    // Libs
    implementation(libs.core.splashscreen)
}
