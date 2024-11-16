package org.aktasbedir.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import org.aktasbedir.blogmultiplatform.components.AdminPageLayout
import org.aktasbedir.blogmultiplatform.utils.isUserLoggedIn

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
    AdminPageLayout{}
}