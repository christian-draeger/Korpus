package codes.draeger.korpus

import io.github.allangomes.kotlinwind.css.I500
import io.github.allangomes.kotlinwind.css.LG
import io.github.allangomes.kotlinwind.css.SM
import io.github.allangomes.kotlinwind.css.kw
import kotlinx.html.BODY
import kotlinx.html.p
import kotlinx.html.style

fun BODY.sharedViewComponentWithStyling() =
    p {
        style = kw.inline {
            text.red[I500].center.middle
            underline.blue[I500].dotted.offset_4
            border[4].y[1]
            padding[LG].x[SM]
        }
        +"This is a shared component styled with Kotlinwind.css"
    }.toString().trim()
