package org.aktasbedir.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.core.Page
import org.aktasbedir.blogmultiplatform.components.SidePanel
import org.aktasbedir.blogmultiplatform.utils.Constants.PAGE_MAX_WIDTH
import org.aktasbedir.blogmultiplatform.utils.isUserLoggedIn
import org.jetbrains.compose.web.css.px

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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .maxWidth(PAGE_MAX_WIDTH.px)
        ) {
            SidePanel()
        }
    }}