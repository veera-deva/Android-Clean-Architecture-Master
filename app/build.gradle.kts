@file:Suppress("UnstableApiUsage")

import deps.Dependencies
import deps.ProjectModules
import deps.TestDependencies

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("org.sonarqube")
}

android {
    namespace = deps.Configs.nameSpace
    compileSdk = deps.AndroidSettings.compileSdk

    defaultConfig {
        applicationId = deps.Configs.applicationId
        minSdk = deps.AndroidSettings.minSdk
        targetSdk = deps.AndroidSettings.targetSdk
        versionCode = deps.Configs.versionCode
        versionName = deps.Configs.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
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
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(ProjectModules.domain))
    implementation(project(ProjectModules.commonUi))
    implementation(project(ProjectModules.featureMovies))

    /*AndroidX dependencies*/
    with(Dependencies.AndroidX) {
        implementation(coreKtx)
        implementation(appCompat)
        implementation(constrainLayout)
        api(navigationFragment)
        api(navigationUi)
        api(navigationFeaturesFragment)
    }

    /*Coroutines*/
    implementation(Dependencies.Kotlin.coroutinesCore)
    /*Material design dependencies*/
    implementation(Dependencies.Google.material)

    /*Android HILT dependencies*/
    with(Dependencies.Hilt) {
        implementation(hiltAndroid)
        kapt(kaptHiltAndroidCompiler)

    }
    /*Hilt Testing dependencies*/
    with(TestDependencies.Hilt) {
        androidTestImplementation(hiltAndroidTesting)
        kaptAndroidTest(hiltAndroidCompiler)
        androidTestAnnotationProcessor(hiltAndroidCompiler)
    }

    /*Android Core unit test dependencies*/
    testImplementation(TestDependencies.JUnit.junit)
    androidTestImplementation(TestDependencies.AndroidX.junit)
    androidTestImplementation(TestDependencies.AndroidX.espressoCore)

    /*Mockito test dependencies*/
    with(TestDependencies.Mockito) {
        testImplementation(mockitoCore)
        testImplementation(mockitoInline)
        androidTestImplementation(mockitoAndroid)
    }

    /*Coroutines test dependencies*/
    testImplementation(TestDependencies.kotlinxCoroutinesTest)

    /*Turbine library to test kotlin flows */
    testImplementation(TestDependencies.turbine)

    testImplementation(project(ProjectModules.sharedTest))
}


sonarqube {
    properties {
        property("sonar.projectName", "Movies-Demo")
        property("sonar.projectKey", "Movies-Demo")
        property("sonar.host.url", "http://localhost:9000")
        property("sonar.tests", { "src/test/java" })
        property("sonar.test.inclusions", "**/*Test*/**")
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.sources", "src/main/java")
        property("sonar.login", "admin")
        property("sonar.password", "admin")
        property(
            "sonar.exclusions", "**/*Test*/**," + "*.json," + "**/*test*/**," +
                    "**/.gradle/**," +
                    "**/R.class"
        )
    }
}

