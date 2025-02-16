package codes.draeger.korpus

import emotion.react.css
import io.github.allangomes.kotlinwind.css.kw
import kotlinx.html.body
import kotlinx.html.stream.createHTML
import kotlinx.rpc.krpc.streamScoped
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import react.useEffectOnce
import react.useState
import web.cssom.Display
import web.cssom.FlexDirection
import web.cssom.NamedColor
import web.cssom.px

data class WelcomeData(
    val serverGreeting: String,
)

external interface WelcomeProps : Props {
    var service: MyService
    var data: WelcomeData
}

fun useArticles(service: MyService): List<String> {
    var articles by useState(emptyList<String>())

    useEffectOnce {
        var localArticles = articles
        streamScoped {
            service.subscribeToNews().collect {
                @Suppress("SuspiciousCollectionReassignment")
                localArticles += it
                articles = localArticles
            }
        }
    }

    return articles
}

val Welcome = FC<WelcomeProps> { props ->
    val articles = useArticles(props.service)

    div {

        css {
            display = Display.flex
            flexDirection = FlexDirection.row
            gap = 4.px
        }

        div {
            css {
                fontSize = 32.px
                lineHeight = 32.px
            }

            +"Project Korpus"
        }
    }

    div {
        +"text.middle compiled by kotlinwind: ${kw.inline { text.middle }})"
    }

    div {
        +"shared component with styling: ${createHTML().body { sharedViewComponentWithStyling() }}"
    }

    div {
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            padding = 5.px
            backgroundColor = NamedColor.orange

            marginTop = 16.px
        }

        p {
            +"Hello! Message from server to you: ${props.data.serverGreeting}"
        }

        p {
            +"Latest news:"
        }

        for (article in articles) {
            p {
                key = article

                +article
            }
        }
    }
}