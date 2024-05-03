plugins {
    id("com.android.application")
}

android {
    namespace = "com.moutamid.readnumberplates"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.moutamid.readnumberplates"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        setProperty("archivesBaseName", "CheckPost-Log-$versionName")
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
    implementation("com.google.android.gms:play-services-mlkit-text-recognition:19.0.0")

  // To recognize Chinese script
//  implementation 'com.google.android.gms:play-services-mlkit-text-recognition-chinese:16.0.0'

  // To recognize Devanagari script
//  implementation 'com.google.android.gms:play-services-mlkit-text-recognition-devanagari:16.0.0'

  // To recognize Japanese script
//  implementation 'com.google.android.gms:play-services-mlkit-text-recognition-japanese:16.0.0'

  // To recognize Korean script
//  implementation 'com.google.android.gms:play-services-mlkit-text-recognition-korean:16.0.0'

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}