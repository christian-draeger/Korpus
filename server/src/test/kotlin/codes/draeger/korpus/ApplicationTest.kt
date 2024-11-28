package codes.draeger.korpus

import io.ktor.server.testing.*
import kotlinx.coroutines.flow.toList
import kotlinx.rpc.krpc.ktor.client.installRPC
import kotlinx.rpc.krpc.ktor.client.rpc
import kotlinx.rpc.krpc.ktor.client.rpcConfig
import kotlinx.rpc.krpc.serialization.json.json
import kotlinx.rpc.krpc.streamScoped
import kotlinx.rpc.withService
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            module()
        }

        val service = createClient {
            installRPC()
        }.rpc("/api") {
            rpcConfig {
                serialization {
                    json()
                }
            }
        }.withService<MyService>()

        assertEquals(
            expected = "Nice to meet you Alex, how is it in address1?",
            actual = service.hello("Alex", UserData("address1", "last")),
        )

        streamScoped {
            assertEquals(
                expected = List(10) { "Article number $it" },
                actual = service.subscribeToNews().toList(),
            )
        }
    }
}