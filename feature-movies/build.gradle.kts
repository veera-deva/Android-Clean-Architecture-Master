@file:Suppress("UnstableApiUsage")

import deps.Dependencies
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    jacoco
}

val jacocoTestReport = tasks.register("jacocoTestReport")

tasks.withType<Test> {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
    }
}
tasks {
    getByName<JacocoReport>("jacocoTestReport") { // HERE using the eager getByName<T>(name) {} instead
        reports {
            xml.enabled = true
            html.enabled = true
        }
        afterEvaluate {
            classDirectories = files(classDirectories.files.map {
                fileTree(it) {
                    exclude("**/config/**", "**/entity/**")
                }
            })
        }
    }
}

android {
    namespace = "com.demo.feature.movies"
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
    sourceSets {
        getByName("main") {
            java {
                srcDirs("src\\main\\java", "src\\main\\java")
            }
            res {
                srcDirs("src\\main\\res", "src\\main\\res")
            }
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

}
dependencies {

    with(deps.ProjectModules) {
        implementation(project(commonUi))
        implementation(project(data))
        implementation(project(domain))
    }


    /*AndroidX dependencies*/
    with(Dependencies.AndroidX) {
        implementation(coreKtx)
        implementation(appCompat)

        /*Navigation dependencies*/
        api(navigationFragment)
        api(navigationUi)
        api(navigationFeaturesFragment)
    }

    implementation(Dependencies.Google.material)

    /*Android HILT dependencies*/
    with(Dependencies.Hilt) {
        implementation(hiltAndroid)
        kapt(kaptHiltAndroidCompiler)

    }


    /*Android Core unit test dependencies*/
    with(deps.TestDependencies.AndroidX) {
        testImplementation(deps.TestDependencies.JUnit.junit)
        androidTestImplementation(junit)
        androidTestImplementation(espressoCore)
    }

    /*Hilt Testing dependencies*/
    with(deps.TestDependencies.Hilt) {
        androidTestImplementation(hiltAndroidTesting)
        kaptAndroidTest(hiltAndroidCompiler)
        androidTestAnnotationProcessor(hiltAndroidCompiler)
    }
    /*Mockito test dependencies*/
    with(deps.TestDependencies.Mockito) {
        testImplementation(mockitoCore)
        testImplementation(mockitoInline)
        androidTestImplementation(mockitoAndroid)
    }
    /*Coroutines test dependencies*/
    testImplementation(deps.TestDependencies.kotlinxCoroutinesTest)

    /*Flow unit testing dependencies*/
    testImplementation(deps.TestDependencies.turbine)

    /*Shared test module*/
    testImplementation(project(deps.ProjectModules.sharedTest))

    /*Code coverage dependency*/
    testImplementation(deps.TestDependencies.jacoco)
}
