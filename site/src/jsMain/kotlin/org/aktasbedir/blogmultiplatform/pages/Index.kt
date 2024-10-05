package org.aktasbedir.blogmultiplatform.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.dom.Text
import com.varabyte.kobweb.worker.rememberWorker
import org.aktasbedir.blogmultiplatform.utils.isUserLoggedIn
import org.aktasbedir.blogmultiplatform.worker.EchoWorker

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
    println("Admin Home Page")
}