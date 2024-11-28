package codes.draeger.korpus

import react.create
import react.dom.client.createRoot
import web.dom.document

fun main() {
    val container = document.createElement("div")
    document.body.appendChild(container)

    val app = App.create()
    createRoot(container).render(app)
}