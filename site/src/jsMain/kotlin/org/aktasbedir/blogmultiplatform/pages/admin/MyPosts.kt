package org.aktasbedir.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import org.aktasbedir.blogmultiplatform.components.AdminPageLayout
import org.aktasbedir.blogmultiplatform.utils.isUserLoggedIn

@Page
@Composable
fun MyPostsPage() {
    isUserLoggedIn {
        MyPostsScreen()
    }
}
@Composable
fun MyPostsScreen() {
    AdminPageLayout{}
}