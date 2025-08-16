plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.sample.tmdb.detail"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
}

dependencies {
    implementation(project(mapOf("path" to BuildModules.DOMAIN)))

    implementation(Deps.lifecycleViewModel)
    implementation(Deps.lifecycleSavedstate)
    implementation(Deps.coroutinesCore)
    implementation(Deps.coroutinesAndroid)
    implementation(Deps.hilt)
    ksp(Deps.hilt_compiler)
    implementation(Deps.hilt_compose)
    implementation(Deps.composeUi)
    implementation(Deps.composeFoundation)
    implementation(Deps.composeMaterial)
    implementation(Deps.iconExtended)
    implementation(Deps.coil)
    implementation(Deps.palette)
    implementation(Deps.composeConstraintLayout)
    implementation(Deps.browser)
    implementation(Deps.gson)
    testImplementation(project(BuildModules.COMMON_TEST))
    testImplementation(Deps.junit4)
    testImplementation(Deps.mockk)
    androidTestImplementation(Deps.composeUiTest)
    androidTestImplementation(Deps.mockito)
    debugImplementation(Deps.composeManifest)
}