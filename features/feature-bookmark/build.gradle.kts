plugins {
    id("com.android.library")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.sample.tmdb.bookmark"
    compileSdk = AppMetaData.compileSdkVersion

    defaultConfig {
        minSdk = AppMetaData.minSdkVersion
        testOptions.targetSdk = AppMetaData.targetSdkVersion


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
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(mapOf("path" to BuildModules.DOMAIN)))

    implementation(Deps.hilt)
    ksp(Deps.hilt_compiler)
    implementation(Deps.composeUi)
    implementation(Deps.composeFoundation)
    implementation(Deps.composeMaterial)
    testImplementation(project(BuildModules.COMMON_TEST))
    testImplementation(Deps.junit4)
    testImplementation(Deps.mockk)
    androidTestImplementation(Deps.composeUiTest)
    androidTestImplementation(Deps.mockito)
    debugImplementation(Deps.composeManifest)
}