plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.demo.data"
    compileSdk = deps.AndroidSettings.compileSdk

    defaultConfig {
        minSdk = deps.AndroidSettings.minSdk

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
}

dependencies {

    implementation(project(deps.ProjectModules.domain))

    /*Android HILT dependencies*/
    with(deps.Dependencies.Hilt) {
        implementation(hiltAndroid)
        kapt(kaptHiltAndroidCompiler)
    }
    /*Kotlin coroutines*/
    implementation(deps.Dependencies.Kotlin.kotlinxCoroutines)
    implementation(deps.Dependencies.Kotlin.coroutinesCore)

    //*Retrofit*//*
    with(deps.Dependencies.Network) {
        implementation(retrofit)
        implementation(retrofitMoshiConvertor)
        implementation(okhttpLoggingInterceptor)
    }
    /*Testing dependencies*/

    testImplementation(deps.TestDependencies.JUnit.junit)

    /*Mockito dependencies*/
    with(deps.TestDependencies.Mockito) {
        testImplementation(mockitoCore)
        testImplementation(mockitoInline)
    }
    with(deps.TestDependencies) {
        /*Turbine library to test kotlin flows */
        testImplementation(turbine)
        testImplementation(striktCore)

        /*Coroutines test dependencies*/
        testImplementation(kotlinxCoroutinesTest)
    }
    implementation(kotlin("reflect"))

}