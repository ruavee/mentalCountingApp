plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    kotlin("kapt") version "2.1.20"
    id("com.google.dagger.hilt.android") version "2.56.1" apply false
    id("io.gitlab.arturbosch.detekt") version "1.22.0"
}

detekt {
    config = files("$rootDir/config/detekt/detekt.yml")
    buildUponDefaultConfig = true
}

buildscript {
    dependencies {
        classpath(libs.hilt.android.gradle.plugin)
    }
}