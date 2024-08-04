package org.aktasbedir.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import org.aktasbedir.blogmultiplatform.models.Theme
import org.aktasbedir.blogmultiplatform.utils.Constants.FONT_FAMILY
import org.aktasbedir.blogmultiplatform.utils.Resource
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Input


//@Page(routeOverride = "login") gerek yoktu zaten dosya adi Login
@Page(routeOverride = "login")
@Composable
fun LoginScreen() {
    var errorText by remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .padding(leftRight = 50.px, bottom = 24.px)
                .backgroundColor(Theme.LightGrayColor.rgb),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                src = Resource.Image.admin,
                description = "Logo Image",
                modifier = Modifier
                    .padding(bottom = 24.px)
                    .fillMaxSize()
            )
            Input(
                type = InputType.Text,
                attrs = Modifier
                    .width(390.px)
                    .height(40.px)
                    .padding(leftRight = 20.px)
                    .margin(bottom = 12.px)
                    .backgroundColor(Color.white)
                    .borderRadius(4.px)
                    .backgroundColor(Color.white)
                    .fontFamily(FONT_FAMILY)
                    .border(
                        width = 0.px,
                        color = Color.transparent,
                        style = LineStyle.None
                    )
                    .outline(
                        width = 0.px,
                        color = Color.transparent,
                        style = LineStyle.None
                    )
                    .toAttrs {
                        attr("placeholder", "Username")
                    }

            )

            Input(
                type = InputType.Password,
                attrs = Modifier
                    .width(390.px)
                    .height(40.px)
                    .padding(leftRight = 20.px)
                    .margin(bottom = 20.px)
                    .borderRadius(4.px)
                    .backgroundColor(Color.white)
                    .fontFamily(FONT_FAMILY)
                    .fontWeight(FontWeight.Medium)
                    .border(
                        width = 0.px,
                        color = Color.transparent,
                        style = LineStyle.None
                    )
                    .outline(
                        width = 0.px,
                        color = Color.transparent,
                        style = LineStyle.None
                    )
                    .toAttrs {
                        attr("placeholder", "Password")
                    }
            )
            Button(
                attrs = Modifier
                    .width(390.px)
                    .height(40.px)
                    .margin(bottom = 20.px)
                    .backgroundColor(Theme.PrimaryColor.rgb)
                    .borderRadius(4.px)
                    .fontFamily(FONT_FAMILY)
                    .fontWeight(FontWeight.Medium)
                    .border(
                        width = 0.px,
                        color = Color.transparent,
                        style = LineStyle.None
                    )
                    .outline(
                        width = 0.px,
                        color = Color.transparent,
                        style = LineStyle.None
                    )
                    .toAttrs()
            )
            {
                SpanText(
                    text = "Sign In",
                    modifier = Modifier
                        .fontFamily(FONT_FAMILY)
                        .fontWeight(FontWeight.Medium)
                        .color(Color.white)
                )
            }

            SpanText(
                text = errorText,
                modifier = Modifier
                    .width(390.px)
                    .fontFamily(FONT_FAMILY)
                    .fontWeight(FontWeight.Medium)
                    .color(Color.red)
                    .textAlign(TextAlign.Center)
            )


        }
    }
}

