package org.aktasbedir.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.icons.fa.FaPlus
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.aktasbedir.blogmultiplatform.components.AdminPageLayout
import org.aktasbedir.blogmultiplatform.models.Theme
import org.aktasbedir.blogmultiplatform.navigation.Screen
import org.aktasbedir.blogmultiplatform.utils.Constants.PAGE_MAX_WIDTH
import org.aktasbedir.blogmultiplatform.utils.isUserLoggedIn
import org.jetbrains.compose.web.css.Position
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
        AddButton()
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