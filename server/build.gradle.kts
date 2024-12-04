plugins {
    kotlin("jvm")
    alias(libs.plugins.kotlin.plugin.serialization)
    application
    distribution
}

group = "codes.draeger.korpus"
version = "1.0.0"

application {
    mainClass.set("io.ktor.server.cio.EngineMain")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(projects.common)
    implementation(libs.ktor.server.cio)
    implementation(libs.ktor.server.core.jvm)
    implementation(libs.ktor.server.cors.jvm)
    implementation(libs.ktor.server.websockets.jvm)
    implementation(libs.ktor.server.config.yaml)

    implementation(libs.kotlinx.coroutines.core.jvm)
    implementation(libs.logback.classic)

    implementation(libs.kotlinx.rpc.krpc.ktor.server)
    implementation(libs.kotlinx.rpc.krpc.serialization.json)

    testImplementation(libs.kotlinx.rpc.krpc.client)
    testImplementation(libs.kotlinx.rpc.krpc.ktor.client)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.ktor.server.test.host)
}

val buildAndCopyFrontend = tasks.register<Copy>("buildAndCopyFrontend") {
    val frontendDist = project(":frontend").tasks.named("jsBrowserProductionWebpack")
    dependsOn(frontendDist)
    from(frontendDist)
    into("${project.projectDir}/src/main/resources/static")
}

val prepareAppResources = tasks.register("prepareAppResources") {
    dependsOn(buildAndCopyFrontend)
    finalizedBy("processResources")
}

val buildApp = tasks.register("buildApp") {
    dependsOn(prepareAppResources)
    finalizedBy("assemble")
}

tasks.create("runApp") {
    group = "application"
    dependsOn(buildApp)
    finalizedBy(tasks.named("run"))
}