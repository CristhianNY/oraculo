import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.cristhianbonilla.oraculo"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.cristhianbonilla.oraculo"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    flavorDimensions("version")

    productFlavors {
        create("dev") {
            dimension = "version"
            signingConfig = signingConfigs.getByName("debug")
            buildConfigField("String", "GPT3_BASE_URL", '\"' + "https://api.openai.com/" + '\"')
            buildConfigField("String", "BASE_URL", '\"' + "https://sermonsbackend.herokuapp.com/" + '\"')
            buildConfigField("String", "PRIVATE_KEY", "\"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\"")
            buildConfigField("String", "OPENAI_ORGANIZATION", '\"' + "org-4ocQd9c7zqlbmwhZZAjBknOi" + '\"')
            resValue("string", "app_name", "DEV APPAI")
        }

        create("prod") {
            dimension = "version"
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "GPT3_BASE_URL", '\"' + "https://api.openai.com/" + '\"')
            buildConfigField("String", "BASE_URL", '\"' + "https://sermonsbackend.herokuapp.com/" + '\"')
            buildConfigField("String", "PRIVATE_KEY", "\"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\"")
            buildConfigField("String", "OPENAI_ORGANIZATION", '\"' + "org-4ocQd9c7zqlbmwhZZAjBknOi" + '\"')

            resValue("string", "app_name", "AppAI")
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}


dependencies {
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("com.google.dagger:hilt-android:2.44")
    implementation("org.chromium.net:cronet-api:108.5359.79")
    kapt("com.google.dagger:hilt-compiler:2.44")
    implementation ("com.google.code.gson:gson:2.8.7")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-runtime-ktx:2.5.3")
    implementation("com.airbnb.android:lottie-compose:4.0.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.7.1")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.10")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}