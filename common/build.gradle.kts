plugins {
    id("com.android.library")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.sample.tmdb.common"
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
    implementation(Deps.hilt)
    ksp(Deps.hilt_compiler)
    implementation(Deps.androidXCore)
    implementation(Deps.lifecycleViewModel)
    implementation(Deps.composeUi)
    implementation(Deps.composeFoundation)
    implementation(Deps.composeMaterial)
    implementation(Deps.swiperefresh)
    debugImplementation(Deps.composeUiTooling)
    implementation(Deps.composeUiToolingPreview)
    implementation(Deps.coil)
    implementation(Deps.iconExtended)
    implementation(Deps.appcompat)
    androidTestImplementation(Deps.composeUiTest)
    androidTestImplementation(project(BuildModules.DOMAIN))
    debugImplementation(Deps.composeManifest)
}