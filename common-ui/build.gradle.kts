@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.demo.common_ui"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        viewBinding = true
        dataBinding = true
    }
    sourceSets {
        getByName("main") {
            res {
                srcDirs("src\\main\\res", "src\\main\\res")
            }
        }
    }
}

dependencies {

    /*AndroidX dependencies*/
    with(deps.Dependencies.AndroidX) {
        implementation(coreKtx)
        implementation(appCompat)
    }

    /*Material design dependencies*/
    implementation(deps.Dependencies.Google.material)

    /*Android HILT dependencies*/
    with(deps.Dependencies.Hilt) {
        implementation(hiltAndroid)
        kapt(kaptHiltAndroidCompiler)

    }
    //Glide for image rendering
    with(deps.Dependencies) {
        implementation(glide)
        kapt(glideCompiler)
    }
    /*Android Core unit test dependencies*/
    testImplementation(deps.TestDependencies.JUnit.junit)
    with(deps.TestDependencies.AndroidX) {
        androidTestImplementation(junit)
        androidTestImplementation(espressoCore)
    }

}