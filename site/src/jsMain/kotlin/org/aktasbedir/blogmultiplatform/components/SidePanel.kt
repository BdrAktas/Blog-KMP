package org.aktasbedir.blogmultiplatform.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.dom.svg.Path
import com.varabyte.kobweb.compose.dom.svg.Svg
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaBars
import com.varabyte.kobweb.silk.components.icons.fa.FaXmark
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.aktasbedir.blogmultiplatform.models.Theme
import org.aktasbedir.blogmultiplatform.navigation.Screen
import org.aktasbedir.blogmultiplatform.styles.NavigationItemStyle
import org.aktasbedir.blogmultiplatform.utils.Constants.COLLAPSED_PANEL_HEIGHT
import org.aktasbedir.blogmultiplatform.utils.Constants.FONT_FAMILY
import org.aktasbedir.blogmultiplatform.utils.Constants.SIDE_PANEL_WIDTH
import org.aktasbedir.blogmultiplatform.utils.Id
import org.aktasbedir.blogmultiplatform.utils.Res
import org.aktasbedir.blogmultiplatform.utils.logout
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh


@Composable
fun SidePanel(onMenuClick: () -> Unit = {}) {
    //breakpoint: ekran boyutuna gore deger dondurur
    // basically ekran boyutu degistiginde cagrilir
    val breakpoint = rememberBreakpoint()
    //md ve daha kucuk ekransa collapsed goster, md: means devices larger than (small) tablets
    if (breakpoint > Breakpoint.MD) {
        SidePanelInternal()
    } else {
        CollapsedSidePanel(onMenuClick = onMenuClick)
    }
}

@Composable
private fun SidePanelInternal() {

    Column(
        modifier = Modifier
            .padding(leftRight = 40.px, topBottom = 50.px)
            .width(SIDE_PANEL_WIDTH.px)
            //viewport height: ekranın görünür yüksekliğine göre ölçülen bir birimdir
            // 100vh ekranın 100% yüksekliğine esittir
            .height(100.vh)
            .position(Position.Fixed)
            .backgroundColor(Theme.Secondary.rgb)
            //bu diger bilesenlerin ustunde olsun demek
            .zIndex(9)
    ) {
        Image(
            modifier = Modifier.margin(bottom = 60.px),
            src = Res.Image.logo,
            description = "Logo Image"
        )
        //        Image(
//            modifier = Modifier.margin(bottom = 60.px)
//                .width(180.px)
//                .backgroundColor(Color.white),
//            src = Res.Image.admin,
//            description = "Logo Image"
//        )
//        SpanText(
//            modifier = Modifier
//                .margin(bottom = 30.px)
//                .fontFamily(FONT_FAMILY)
//                .fontSize(14.px)
//                .color(Theme.HalfWhite.rgb),
//            text = "Dashboard"
//        )
//        NavigationItem(
//            modifier = Modifier.margin(bottom = 24.px),
//            title = "Home",
//            selected = context.route.path == Screen.AdminHome.route,
//            icon = Res.PathIcon.home,
//            onClick = {
//                context.router.navigateTo(Screen.AdminHome.route)
//            }
//        )
//        NavigationItem(
//            modifier = Modifier.margin(bottom = 24.px),
//            selected = context.route.path == Screen.AdminCreate.route,
//            title = "Create Post",
//            icon = Res.PathIcon.create,
//            onClick = {
//                context.router.navigateTo(Screen.AdminCreate.route)
//            }
//        )
//        NavigationItem(
//            modifier = Modifier.margin(bottom = 24.px),
//            selected = context.route.path == Screen.AdminMyPosts.route,
//            title = "My Posts",
//            icon = Res.PathIcon.posts,
//            onClick = {
//                context.router.navigateTo(Screen.AdminMyPosts.route)
//            }
//        )
//        NavigationItem(
//            title = "Logout",
//            icon = Res.PathIcon.logout,
//            onClick = {
//                logout()
//                context.router.navigateTo(Screen.AdminLogin.route)
//            }
//        )
        NavigationItems()
    }
}

@Composable
private fun NavigationItems() {
    val context = rememberPageContext()

    SpanText(
        modifier = Modifier
            .margin(bottom = 30.px)
            .fontFamily(FONT_FAMILY)
            .fontSize(14.px)
            .color(Theme.HalfWhite.rgb),
        text = "Dashboard"
    )
    NavigationItem(
        modifier = Modifier.margin(bottom = 24.px),
        title = "Home",
        selected = context.route.path == Screen.AdminHome.route,
        icon = Res.PathIcon.home,
        onClick = {
            context.router.navigateTo(Screen.AdminHome.route)
        }
    )
    NavigationItem(
        modifier = Modifier.margin(bottom = 24.px),
        selected = context.route.path == Screen.AdminCreate.route,
        title = "Create Post",
        icon = Res.PathIcon.create,
        onClick = {
            context.router.navigateTo(Screen.AdminCreate.route)
        }
    )
    NavigationItem(
        modifier = Modifier.margin(bottom = 24.px),
        selected = context.route.path == Screen.AdminMyPosts.route,
        title = "My Posts",
        icon = Res.PathIcon.posts,
        onClick = {
            context.router.navigateTo(Screen.AdminMyPosts.route)
        }
    )
    NavigationItem(
        title = "Logout",
        icon = Res.PathIcon.logout,
        onClick = {
            logout()
            context.router.navigateTo(Screen.AdminLogin.route)
        }
    )
}
@Composable
fun NavigationItem(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    title: String,
    icon: String,
    onClick: () -> Unit
) {
    Row(
        modifier = NavigationItemStyle
            .toModifier()
            .then(modifier)
            .cursor(Cursor.Pointer)
            .onClick { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        VectorIcon(
            modifier = Modifier.margin(right = 10.px),
            selected = selected,
            pathData = icon
        )
        SpanText(
            modifier = Modifier
                .id(Id.navigationText)
                .fontFamily(FONT_FAMILY)
                .fontSize(16.px)
                .thenIf(
                    condition = selected,
                    other = Modifier.color(Theme.Primary.rgb)
                ),
            text = title
        )
    }
}

@Composable
fun VectorIcon(
    modifier: Modifier = Modifier,
    selected: Boolean,
    pathData: String
) {
    Svg(
        attrs = modifier
            .id(Id.svgParent)
            .width(24.px)
            .height(24.px)
            .toAttrs {
                attr("viewBox", "0 0 24 24")
                attr("fill", "none")
            }
    ) {
        Path(
            attrs = Modifier
                .id(Id.vectorIcon)
                .thenIf(
                    condition = selected,
                    other = Modifier.styleModifier {
                        property("stroke", Theme.Primary.hex)
                    }
                )
                .toAttrs {
                    attr("d", pathData)
                    attr("stroke-width", "2")
                    attr("stroke-linecap", "round")
                    attr("stroke-linejoin", "round")
                }
        )
    }
}

@Composable
// Hamburger menuye tiklayinca onMenuClick lambda function calisacak
fun CollapsedSidePanel(onMenuClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(COLLAPSED_PANEL_HEIGHT.px)
            .padding(leftRight = 24.px)
            .backgroundColor(Theme.Secondary.rgb),
        verticalAlignment = Alignment.CenterVertically
    ) {
        //Kobweb bize font awesome iconlarını kullanma ozgurlugu veriyor
        // bizim kullandigimiz bars iconu: https://fontawesome.com/search?q=bars&o=r
        FaBars(
            modifier = Modifier
                .margin(right = 24.px)
                .color(Colors.White)
                .cursor(Cursor.Pointer)
                .onClick { onMenuClick() },
            size = IconSize.XL
        )
        Image(
            modifier = Modifier.width(80.px),
            src = Res.Image.logo,
            description = "Logo Image"
        )
    }
}

@Composable
fun OverflowSidePanel(onMenuClose: () -> Unit) {
    val breakpoint = rememberBreakpoint()

    //eger tabletten buyukse menuyi otamatic olarak kapat
    LaunchedEffect(key1 = breakpoint) {
        if (breakpoint > Breakpoint.MD) {
            onMenuClose()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.vh)
            .position(Position.Fixed)
            .zIndex(9)
            .backgroundColor(Theme.HalfBlack.rgb)
    ) {
        Column(
            modifier = Modifier
                .padding(all = 24.px)
                .fillMaxHeight()
                //burda direk 250 yerine percantage kullandik
                .width(if (breakpoint < Breakpoint.MD) 50.percent else 25.percent)
                .backgroundColor(Theme.Secondary.rgb)
        ) {
            Row(
                modifier = Modifier.margin(bottom = 60.px),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //icon: https://fontawesome.com/search?q=xmark&o=r
                FaXmark(
                    modifier = Modifier
                        .margin(right = 20.px)
                        .color(Colors.White)
                        .cursor(Cursor.Pointer)
                        .onClick {
                            onMenuClose()
                        },
                    size = IconSize.LG
                )
                Image(
                    modifier = Modifier.width(80.px),
                    src = Res.Image.logo,
                    description = "Logo Image"
                )
            }
            NavigationItems()
        }
    }
}