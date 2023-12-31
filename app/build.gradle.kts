plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.oop_task"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.oop_task"
        minSdk = 21
        targetSdk = 33
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
    viewBinding {
        enable = true
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
    val coroutines_android_version = "1.6.4"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_android_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_android_version")

    // lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    val room_version = "2.6.0"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    val fragment_version = "1.6.2"
    // Kotlin
    implementation("androidx.fragment:fragment-ktx:$fragment_version")

    // reflection-free flavor
    implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.9")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.+")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.16.+")
    implementation("com.fasterxml.woodstox:woodstox-core:6.5.1")
    implementation("javax.xml.stream:stax-api:1.0-2")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}