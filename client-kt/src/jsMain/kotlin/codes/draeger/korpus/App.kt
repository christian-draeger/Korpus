package codes.draeger.korpus

import kotlinx.rpc.RPCClient
import kotlinx.rpc.withService
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.useEffectOnce
import react.useState

val App = FC<Props> {
    var rpcClient by useState<RPCClient?>(null)

    useEffectOnce {
        rpcClient = initRpcClient()
    }

    rpcClient?.also { client ->
        AppContainer {
            this.apiService = client.withService<MyService>()
        }
    } ?: run {
        div {
            +"Establishing connection..."
        }
    }
}

external interface AppContainerProps : Props {
    var apiService: MyService
}

val AppContainer = FC<AppContainerProps> { props ->
    val service by useState(props.apiService)
    var data by useState<WelcomeData?>(null)

    useEffectOnce {
        val greeting = service.hello("Alex", UserData("Berlin", "Smith"))
        data = WelcomeData(greeting)
    }

    data?.also { welcomeData ->
        Welcome {
            this.data = welcomeData
            this.service = service
        }
    } ?: run {
        div {
            +"Loading data..."
        }
    }
}
