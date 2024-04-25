plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

buildscript {
    repositories {
        // Make sure that you have the following two repositories
        gradlePluginPortal()
        google()  // Google's Maven repository

        mavenCentral()  // Maven Central repository

    }
    dependencies {

        // Add the dependency for the Google services Gradle plugin
        classpath("com.google.gms:google-services:4.4.1")
//        id 'com.google.android.libraries.mapsplatform.secrets-gradle-plugin' version '2.0.1' apply false

        classpath("io.realm:realm-gradle-plugin:10.11.1")
    }
}