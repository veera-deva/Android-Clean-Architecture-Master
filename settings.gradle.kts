pluginManagement {
    plugins {
        id("org.jetbrains.kotlin.jvm") version "1.8.0"
        id("org.jetbrains.kotlin.android") version "1.8.0"
        id("com.android.library") version "7.4.2"
    }
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Movies-Clean-Demo"
include(":app")
include(":domain")
include(":data")
include(":feature-movies")
include(":common-ui")
include(":shared-test")
