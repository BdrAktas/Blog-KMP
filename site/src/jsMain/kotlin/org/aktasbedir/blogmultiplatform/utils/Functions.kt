package org.aktasbedir.blogmultiplatform.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.core.rememberPageContext
import kotlinx.browser.localStorage
import org.w3c.dom.Screen
import org.w3c.dom.get

@Composable
// parametre olarak aldigi lambda blogu aslinda tum sayfayi refer ediyor
fun isUserLoggedIn(content: @Composable () -> Unit) {
    val context = rememberPageContext()
    val remembered = remember { localStorage["isLoggedIn"].toBoolean() }
    val userId = remember { localStorage["userId"] }
    var userIdExists by remember { mutableStateOf(false) }

    // (key1 = Unit) -> key1 parametresi ile sadece bir kez calisacagimizi belirtiyoruz
    LaunchedEffect(key1 = Unit) {
        //userId localStorage'da varsa only then call checkUserId function with that userId
        // eger yoksa direk falase don
        userIdExists = if (!userId.isNullOrEmpty()) checkUserId(id = userId) else false
        if (!remembered || !userIdExists) {
            context.router.navigateTo("/admin/login")
        }
    }

    if (remembered && userIdExists) {
        content()
    } else {
        println("Loading...")
    }
}