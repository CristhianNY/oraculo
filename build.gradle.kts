// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0-alpha03" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}

buildscript {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.42")
    }
}
