package deps

import deps.Versions.appCompatVersion
import deps.Versions.constraintLayoutVersion
import deps.Versions.coreKTXVersion
import deps.Versions.hiltAndroidVersion
import deps.Versions.hiltKaptCompilerVersion
import deps.Versions.kotlinxCoroutinesVersion
import deps.Versions.materialVersion
import deps.Versions.okHttpLoggingInterceptorVersion
import deps.Versions.retrofitVersion

object Dependencies {

    object AndroidX {
        val coreKtx by lazy { "androidx.core:core-ktx:${coreKTXVersion}" }
        val appCompat by lazy { "androidx.appcompat:appcompat:${appCompatVersion}" }
        val material by lazy { "com.google.android.material:material:${materialVersion}" }
        val constrainLayout by lazy {
            "androidx.constraintlayout:constraintlayout:${constraintLayoutVersion}"
        }
        val navigationFragment by lazy {
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
        }
        val navigationFeaturesFragment by lazy {
            "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigationVersion}"
        }
        val navigationUi by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}" }
    }


    object Google {
        val material by lazy { "com.google.android.material:material:$materialVersion" }
    }

    object Kotlin {
        val kotlinxCoroutines by lazy {
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${kotlinxCoroutinesVersion}"
        }
        val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${Versions.coroutinesCoreJVM}" }
    }

    object Hilt {
        val hiltAndroid by lazy { "com.google.dagger:hilt-android:${hiltAndroidVersion}" }
        val kaptHiltAndroidCompiler by lazy {
            "com.google.dagger:hilt-android-compiler:${hiltKaptCompilerVersion}"
        }
    }

    object Network {
        val retrofit by lazy { "com.squareup.retrofit2:retrofit:${retrofitVersion}" }
        val retrofitMoshiConvertor by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.retrofitMoshi}" }

        val okhttpLoggingInterceptor by lazy {
            "com.squareup.okhttp3:logging-interceptor:${okHttpLoggingInterceptorVersion}"
        }
    }

    //Glide for image rendering
    val glide by lazy { "com.github.bumptech.glide:glide:${Versions.glideVersion}" }
    val glideCompiler by lazy { "com.github.bumptech.glide:compiler:${Versions.glideVersion}" }

}