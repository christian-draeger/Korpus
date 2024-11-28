plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.kotlinx.rpc)
}

kotlin {
    jvm()
    js {
        browser()
        binaries.executable()
    }

    // trying to fix it - Common.kt can find dependencies now
    // getting exception when trying to run server:  Exception in thread "main" io.ktor.server.engine.internal.ReloadingException: Module function cannot be found for the fully qualified name 'ApplicationKt.module'
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.stdlib)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.rpc.core)
        }
    }
}

// taken from krpc docs but can not use dependencies than in commonMain

//dependencies {
//    commonMainApi(libs.kotlin.stdlib)
//    commonMainApi(libs.kotlinx.serialization.json)
//    commonMainApi(libs.ktor.client.core)
//    commonMainApi(libs.kotlinx.coroutines.core)
//    commonMainApi(libs.kotlinx.rpc.core)
//}