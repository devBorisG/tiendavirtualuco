plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp") version "1.9.10-1.0.13"
    id("androidx.room") version "2.7.0-alpha10"
    alias(libs.plugins.googleGmsGoogleServices)



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

        // Leer las variables de entorno
        val apiCredentials: String = (project.findProperty("API_CREDENTIALS") as? String) ?: ""
        buildConfigField("String", "API_CREDENTIALS", "\"$apiCredentials\"")
    }

    buildFeatures {
        buildConfig = true
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
    // Configuración del directorio de esquemas
    schemaDirectory("$projectDir/schemas")
}

ksp {
    // Argumento para que Room genere los esquemas
    arg("room.schemaLocation", "$projectDir/schemas")
}

tasks.matching { task ->
    task.name.contains("copyRoomSchemasToAndroidTestAssets")
}.configureEach {
    this.enabled = false
}

dependencies {
    implementation("com.google.firebase:firebase-database-ktx:20.2.2")
    // Firebase Realtime Database
    implementation("com.google.firebase:firebase-database:20.4.2")

    // Opcional: Firebase Authentication (si necesitas autenticación)
    implementation("com.google.firebase:firebase-auth:22.1.2")

    // Firebase Core para el seguimiento analítico (opcional)
    implementation("com.google.firebase:firebase-analytics-ktx:21.3.0")
    implementation("com.jakewharton.timber:timber:5.0.1")
    // Retrofit y OkHttp
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // Gson (si es necesario)
    implementation(libs.gson)

    // Encrypted SharedPreferences
    implementation(libs.security.crypto)

    // Lifecycle (opcional)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)

    // Retrofit Coroutines Adapter (opcional)
    implementation(libs.retrofit.coroutines.adapter)

    // Dependencia para el compilador de Room
    implementation("androidx.room:room-runtime:2.5.2")
    ksp("androidx.room:room-compiler:2.5.2")

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
