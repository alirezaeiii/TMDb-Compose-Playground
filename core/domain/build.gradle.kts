plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.sample.tmdb.domain"
    compileSdk = AppMetaData.compileSdkVersion

    defaultConfig {
        minSdk = 21

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
}

dependencies {
    api(project(BuildModules.COMMON))
    implementation(Deps.coroutinesCore)
    implementation(Deps.coroutinesAndroid)
    implementation(Deps.annotation)
    implementation(Deps.composePaging)
    implementation(Deps.retrofit)
    implementation(Deps.hilt)
    kapt(Deps.hilt_compiler)
}