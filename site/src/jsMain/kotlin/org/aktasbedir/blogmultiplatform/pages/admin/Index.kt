package org.aktasbedir.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaPlus
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.aktasbedir.blogmultiplatform.components.AdminPageLayout
import org.aktasbedir.blogmultiplatform.models.Joke
import org.aktasbedir.blogmultiplatform.models.Theme
import org.aktasbedir.blogmultiplatform.navigation.Screen
import org.aktasbedir.blogmultiplatform.utils.Constants.FONT_FAMILY
import org.aktasbedir.blogmultiplatform.utils.Constants.PAGE_MAX_WIDTH
import org.aktasbedir.blogmultiplatform.utils.Constants.SIDE_PANEL_WIDTH
import org.aktasbedir.blogmultiplatform.utils.Res
import org.aktasbedir.blogmultiplatform.utils.isUserLoggedIn
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh

// zaten dosya ismi "Index" oldugu icin  bu spesifik routeun home pageine "website/admin" ile gidebiliriz
@Page
@Composable
fun HomePage() {
    isUserLoggedIn {
        //burda isUserLoggedIn fonksiyonuna content lambda parameteresi gonderioruz
        // eger isUserLoggedIn true ve userIdExist true ise content call ediliyor
        HomeScreen()
    }
}

@Composable
fun HomeScreen() {
    AdminPageLayout{
        HomeContent(
            joke = Joke(
                id = 2,
                joke = "Some random joke....:Some random joke....:Some random joke....")
        )
        AddButton()
    }
}

@Composable
fun HomeContent(joke: Joke?) {
    val breakpoint = rememberBreakpoint()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(left = if(breakpoint > Breakpoint.MD) SIDE_PANEL_WIDTH.px else 0.px),
        contentAlignment = Alignment.Center
    ) {
        if (joke != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(topBottom = 50.px),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (joke.id != -1) {
                    Image(
                        modifier = Modifier
                            .size(150.px)
                            .margin(bottom = 50.px),
                        src = Res.Image.laugh,
                        description = "Laugh Image"
                    )
                }
                // Eger joke Q: ile basliyorsa yani hem hem question hem de answer varsa
                if (joke.joke.contains("Q:")) {
                    SpanText(
                        modifier = Modifier
                            .margin(bottom = 14.px)
                            .fillMaxWidth(60.percent)
                            .textAlign(TextAlign.Center)
                            .color(Theme.Secondary.rgb)
                            .fontSize(28.px)
                            .fontFamily(FONT_FAMILY)
                            .fontWeight(FontWeight.Bold),
                        // Listeki ikinci itemi al demek
                        text = joke.joke.split(":")[1]
                    )
                    SpanText(
                        modifier = Modifier
                            .fillMaxWidth(60.percent)
                            .textAlign(TextAlign.Center)
                            .color(Theme.HalfBlack.rgb)
                            .fontSize(20.px)
                            .fontFamily(FONT_FAMILY)
                            .fontWeight(FontWeight.Normal),
                        text = joke.joke.split(":").last()
                    )
                } else {
                    SpanText(
                        modifier = Modifier
                            .margin(bottom = 14.px)
                            .fillMaxWidth(60.percent)
                            .textAlign(TextAlign.Center)
                            .color(Theme.Secondary.rgb)
                            .fontFamily(FONT_FAMILY)
                            .fontSize(28.px)
                            .fontWeight(FontWeight.Bold),
                        text = joke.joke.split(":")[1]
                    )
                }
            }
        } else {
            // Sonradan buraya farkli bir UI component gelicek
            println("Loading a joke...")
        }
    }
}

@Composable
fun AddButton() {
    val breakpoint = rememberBreakpoint()
    val context = rememberPageContext()
    Box(
        modifier = Modifier
            .height(100.vh)
            .fillMaxWidth()
            .maxWidth(PAGE_MAX_WIDTH.px)
            .position(Position.Fixed)
            //burdaki Box tum ekrani kaplar ve altindaki componentlerin tiklamasini engeller
            // bu yuzden styleModifier koyduk
            .styleModifier {
                property("pointer-events", "none")
            },
        contentAlignment = Alignment.BottomEnd
    ) {
        Box(
            modifier = Modifier
                .margin(
                    right = if (breakpoint > Breakpoint.MD) 40.px else 20.px,
                    bottom = if (breakpoint > Breakpoint.MD) 40.px else 20.px
                )
                .backgroundColor(Theme.Secondary.rgb)
                .size(if (breakpoint > Breakpoint.MD) 80.px else 40.px)
                .borderRadius(r = 14.px)
                .cursor(Cursor.Pointer)
                .onClick {
                    context.router.navigateTo(Screen.AdminCreate.route)
                }
                .styleModifier {
                    property("pointer-events", "auto")
                },
            contentAlignment = Alignment.Center
        ) {
            FaPlus(
                modifier = Modifier.color(Colors.White),
                size = IconSize.LG
            )
        }
    }
}