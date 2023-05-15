plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
}
android {
    namespace = "com.demo.domain"
    compileSdk = 33

    kotlinOptions {
        jvmTarget = "1.8"
    }
}
dependencies {
    /*Android HILT dependencies*/
    with(deps.Dependencies.Hilt) {
        implementation(hiltAndroid)
        kapt(kaptHiltAndroidCompiler)
    }
    /*Coroutines*/
    implementation(deps.Dependencies.Kotlin.coroutinesCore)

    /*Testing dependencies*/
    testImplementation(deps.TestDependencies.JUnit.junit)

    /*Coroutines test dependencies*/
    testImplementation(deps.TestDependencies.kotlinxCoroutinesTest)

    /*Mockito dependencies*/
    with(deps.TestDependencies.Mockito) {
        testImplementation(mockitoCore)
        testImplementation(mockitoInline)
    }

    /*Turbine library to test kotlin flows */
    testImplementation(deps.TestDependencies.turbine)
}