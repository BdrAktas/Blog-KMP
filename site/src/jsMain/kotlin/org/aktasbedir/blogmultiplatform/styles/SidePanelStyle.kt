package org.aktasbedir.blogmultiplatform.styles

import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import org.aktasbedir.blogmultiplatform.models.Theme
import org.aktasbedir.blogmultiplatform.utils.Id
import org.jetbrains.compose.web.css.ms


val NavigationItemStyle by ComponentStyle {
    cssRule(" > #${Id.svgParent} > #${Id.vectorIcon}") {
        Modifier
            .transition(
//                CSSTransition(property = TransitionProperty.All.toString(), duration = 300.ms)
                Transition.of(
                    property = TransitionProperty.All.toString(),
                    duration = 300.ms,
                    timingFunction = null,
                    delay = null
                )
            )
            .styleModifier {
                property("stroke", Theme.White.hex)
            }
    }
    cssRule(":hover > #${Id.svgParent} > #${Id.vectorIcon}") {
        Modifier
            .styleModifier {
                property("stroke", Theme.Primary.hex)
            }
    }
    cssRule(" > #${Id.navigationText}") {
        Modifier
            .transition(
                //                CSSTransition(property = TransitionProperty.All.toString(), duration = 300.ms)
                Transition.of(
                    property = TransitionProperty.All.toString(),
                    duration = 300.ms,
                    timingFunction = null,
                    delay = null
                )
            )

            .color(Theme.White.rgb)
    }
    cssRule(":hover > #${Id.navigationText}") {
        Modifier.color(Theme.Primary.rgb)
    }
}