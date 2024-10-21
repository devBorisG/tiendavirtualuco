plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp") version "1.9.10-1.0.13"
    id("androidx.room") version "2.7.0-alpha10"
}

android {
    namespace = "com.example.tiendavirtualuco"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tiendavirtualuco"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    // Dependencia para el compilador de Room
    ksp(libs.androidx.room.compiler.v270alpha01)

    // Dependencia para el runtime de Room
    implementation(libs.androidx.room.runtime)

    // Dependencia opcional para SQLite empaquetado
    implementation(libs.androidx.sqlite.bundled)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.recyclerview)
    implementation(libs.glide.v4142)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}