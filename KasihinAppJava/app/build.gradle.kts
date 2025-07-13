// File: build.gradle.kts (Module: app)
// Menggunakan sintaks Kotlin DSL

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.kasihinapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.kasihinapp"
        minSdk = 26 // DIUBAH: Menaikkan versi minimum ke 26 (Android Oreo)
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

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(libs.activity)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // --- DEPENDENSI UNTUK GOOGLE SIGN-IN ---
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    // --- DEPENDENSI UNTUK NETWORKING (Contoh: Retrofit & GSON) ---
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // --- DEPENDENSI UNTUK CIRCLE IMAGE VIEW (DITAMBAHKAN DI SINI) ---
    implementation("de.hdodenhof:circleimageview:3.1.0")
}
