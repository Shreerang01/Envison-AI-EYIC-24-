// Top-level build file where you can add configuration options common to all sub-projects/modules.

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
//        id 'com.google.android.libraries.platform.secrets-gradle-plugin' version '2.0.1' apply false

        classpath("io.realm:realm-gradle-plugin:10.11.1")
    }
}

plugins {
    id("com.android.application") version "8.2.0" apply false
    id("io.realm.kotlin") version "1.11.0" apply false

    id("com.android.library") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("io.realm.kotlin") version "1.6.1" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

