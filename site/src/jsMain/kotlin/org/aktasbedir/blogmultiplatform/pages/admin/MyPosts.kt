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

@Page
@Composable
fun MyPostsPage() {
    isUserLoggedIn {
        MyPostsScreen()
    }
}
@Composable
fun MyPostsScreen() {
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
    }
}