package org.aktasbedir.blogmultiplatform.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import org.aktasbedir.blogmultiplatform.navigation.Screen
import org.aktasbedir.blogmultiplatform.utils.isUserLoggedIn

@Page
@Composable
fun HomeScreen() {
    isUserLoggedIn {
        //burda isUserLoggedIn fonksiyonuna content lambda parameteresi gonderioruz
        // eger remembered ve userIdExist true ise content call ediliyor
        HomePage()
    }
}

@Composable
fun HomePage() {
    val context = rememberPageContext()
    context.router.navigateTo(Screen.AdminHome.route)
}