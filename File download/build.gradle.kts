plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.moutamid.sprachelernen"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.moutamid.sprachelernen"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        setProperty("archivesBaseName", "SpracheLernen-$versionName")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures { viewBinding = true }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
// for using PR Downloader library
    implementation("com.mindorks.android:prdownloader:0.6.0")

// for using firebase to download file from firebase storage (only use this library if your file is placed in firebase)
    implementation("com.google.firebase:firebase-storage:20.3.0")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}