pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MBChallenge"

// -=-= keep in alphabetical order =-=-

// App
include(":app")

// Core
include(
    ":core:navigation",
)

// Libraries
include(
    ":libraries:arch",
    ":libraries:design",
)

// -=-= keep in alphabetical order =-=-
