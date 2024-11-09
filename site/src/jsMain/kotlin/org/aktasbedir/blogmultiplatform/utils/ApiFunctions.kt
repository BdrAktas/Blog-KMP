package org.aktasbedir.blogmultiplatform.utils

import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.aktasbedir.blogmultiplatform.models.User
import org.aktasbedir.blogmultiplatform.models.UserWithoutPassword

suspend fun checkUserExistence(user: User): UserWithoutPassword? {
    return try {
        //burdaki result Apiya yollacagimiz post request sonucu olucak
        val result = window.api
            //burda post veya tryPost gibi fonksiyonlarindan birini kullanabiliriz
            // tryPost ise -> try catch blogunda post yapiyor sonuc yoksa nullable BytArray donuyor
            .tryPost(
                apiPath = "usercheck",
                body = Json.encodeToString(user).encodeToByteArray()
            )
       //burda checkUserExistence fonksiyonununun try-catch bloguna almamizin sebebi
       // asagidaki koddan hata firlatabilme olasiligindan dolayi
        result?.decodeToString()?.let { Json.decodeFromString<UserWithoutPassword>(it) }
    } catch (e: Exception) {
        //JS oldugu icin console'a yazdirmak icin direk println kullanabiliriz
        println("CURRENT_USER")
        println(e.message)
        null
    }
}

suspend fun checkUserId(id: String): Boolean {
    return try {
        val result = window.api.tryPost(
            apiPath = "usercheckid",
            body = Json.encodeToString(id).encodeToByteArray()
        )
        result?.decodeToString()?.let { Json.decodeFromString<Boolean>(it) } ?: false
    } catch (e: Exception) {
        println(e.message.toString())
        false
    }
}