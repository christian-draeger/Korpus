plugins {
    kotlin("multiplatform")
    alias(libs.plugins.kotlin.plugin.serialization)
}

kotlin {
    js(IR) {
        binaries.executable()

        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
    }

    sourceSets {
        jsMain {
            dependencies {
                implementation(projects.common)

                implementation(libs.kotlin.stdlib.js)
                implementation(libs.ktor.client.js)
                implementation(libs.ktor.client.websockets.js)
                implementation(libs.kotlinx.rpc.krpc.ktor.client)
                implementation(libs.kotlinx.rpc.krpc.serialization.json)

                implementation(project.dependencies.platform(libs.kotlin.wrappers.bom))
                implementation(libs.react)
                implementation(libs.react.dom)

                implementation(libs.kotlinx.html.js)

                implementation(libs.emotion)
                implementation(libs.kotlinwindJs)
            }
        }
    }
}