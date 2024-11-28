plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlinx.rpc) apply false
}

allprojects {
    version = "0.1"

    repositories {
        mavenCentral()
    }
}