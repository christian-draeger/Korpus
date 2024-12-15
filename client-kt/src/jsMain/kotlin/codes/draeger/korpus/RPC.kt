package codes.draeger.korpus

import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.request.*
import kotlinx.rpc.RPCClient
import kotlinx.rpc.krpc.ktor.client.installRPC
import kotlinx.rpc.krpc.ktor.client.rpc
import kotlinx.rpc.krpc.ktor.client.rpcConfig
import kotlinx.rpc.krpc.serialization.json.json

suspend fun initRpcClient(): RPCClient {
    return HttpClient(Js) {
        installRPC()
    }.rpc {
        url("ws://localhost:8080/api")

        rpcConfig {
            serialization {
                json()
            }
        }
    }
}