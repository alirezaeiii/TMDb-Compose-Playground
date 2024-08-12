plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.sample.tmdb.data"
    compileSdk = AppMetaData.compileSdkVersion

    defaultConfig {
        minSdk = AppMetaData.minSdkVersion
        buildConfigField("String", "TMDB_BASE_URL", "\"http://api.themoviedb.org/\"")
        buildConfigField("String", "TMDB_API_KEY", "\"${getProperty("local.properties", "tmdb_api_key") ?: System.getenv("TMDB_API_KEY")}\"")


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
        buildConfig = true
    }
}

dependencies {
    api(project(BuildModules.DOMAIN))
    implementation(Deps.retrofit)
    implementation(Deps.retrofitMoshi)
    implementation(Deps.okhttpInterceptor)
    implementation(Deps.moshi)
    implementation(Deps.moshiKotlin)
    ksp(Deps.moshiCodegen)
    implementation(Deps.composePaging)
    implementation(Deps.room)
    implementation(Deps.roomKtx)
    ksp(Deps.roomCompiler)
    implementation(Deps.hilt)
    ksp(Deps.hilt_compiler)
    testImplementation(project(BuildModules.COMMON_TEST))
    testImplementation(Deps.junit4)
    testImplementation(Deps.mockito)
    testImplementation(Deps.coroutineTest)
    testImplementation(Deps.turbine)
}

fun getProperty(filename: String, propName: String): String? {
    val propsFile = rootProject.file(filename)
    if (propsFile.exists()) {
        return com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir, providers).getProperty(propName)
    } else {
        print("$filename does not exist!")
    }
    return null
}