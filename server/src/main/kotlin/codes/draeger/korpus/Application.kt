package codes.draeger.korpus

import io.github.allangomes.kotlinwind.css.I50
import io.github.allangomes.kotlinwind.css.I500
import io.github.allangomes.kotlinwind.css.kw
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.style
import kotlinx.rpc.krpc.ktor.server.RPC
import kotlinx.rpc.krpc.ktor.server.rpc
import kotlinx.rpc.krpc.serialization.json.json

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(RPC)

    installCORS()

    routing {
        rpc("/api") {
            rpcConfig {
                serialization {
                    json()
                }
            }

            registerService<MyService> { ctx -> MyServiceImpl(ctx) }
        }

        get("/inline-style") {
            call.respondHtml(HttpStatusCode.OK) {
                body {
                    div {
                        style = kw.inline {
                            height["100vh"]
                            background.gray[I50]
                            text.red[I500].middle.center
                        }
                        +"Styled with Kotlinwind.css"
                    }
                }
            }
        }

        get("/inline-style-shared") {
            call.respondHtml(HttpStatusCode.OK) {
                body {
                    sharedViewComponentWithStyling()
                }
            }
        }

        staticResources("/", "/static") {
            default("client-kt.html")
        }
    }
}

fun Application.installCORS() {
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.Upgrade)
        allowNonSimpleContentTypes = true
        allowCredentials = true
        allowSameOrigin = true

        // webpack-dev-server and local development
        val allowedHosts = listOf("localhost:3000", "localhost:8080", "127.0.0.1:8080")
        allowedHosts.forEach { host ->
            allowHost(host, listOf("http", "https", "ws", "wss"))
        }
    }
}