package org.aktasbedir.blogmultiplatform.utils

import com.varabyte.kobweb.browser.api
import com.varabyte.kobweb.browser.http.http
import kotlinx.browser.localStorage
import kotlinx.browser.window
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.aktasbedir.blogmultiplatform.models.RandomJoke
import org.aktasbedir.blogmultiplatform.models.User
import org.aktasbedir.blogmultiplatform.models.UserWithoutPassword
import org.w3c.dom.get
import org.w3c.dom.set
import kotlin.js.Date

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

suspend fun fetchRandomJoke(onComplete: (RandomJoke) -> Unit) {
    val date = localStorage["date"]
    if (date != null) {
        val difference = (Date.now() - date.toDouble())
        //86400000 ms = 1 day -> eger 1 day'dan fazla olursa yeni bir random joke alacagiz
        val dayHasPassed = difference >= 86400000
        if (dayHasPassed) {
            try {
                // burda window.api.get yerine window.http.get kullandik cunku bizim api yerine 3rd party api kullanacagiz
                val result = window.http.get(Constants.HUMOR_API_URL).decodeToString()
                onComplete(Json.decodeFromString(result))
                // yeni bir random joke aldigimiz icin local storage'da date'i guncelleyecegiz
                localStorage["date"] = Date.now().toString()
                //bunu daha sonra kullanmak uzere random joke'i local storage'da kaydedecegiz
                localStorage["joke"] = result
            } catch (e: Exception) {
                onComplete(RandomJoke(id = -1, joke = e.message.toString()))
                println(e.message)
            }
        } else {
            // decodeFromString exception firlatan bir method oldugu icin try-catch bloguna aldik
            try {
                // eger 1 day'in icindeysek local storage'dan random joke'yi alacagiz
                localStorage["joke"]?.let { Json.decodeFromString<RandomJoke>(it) }
                    ?.let { onComplete(it) }
            } catch (e: Exception) {
                onComplete(RandomJoke(id = -1, joke = e.message.toString()))
                println(e.message)
            }
        }
    } else {
        try {
            val result = window.http.get(Constants.HUMOR_API_URL).decodeToString()
            onComplete(Json.decodeFromString(result))
            localStorage["date"] = Date.now().toString()
            localStorage["joke"] = result
        } catch (e: Exception) {
            onComplete(RandomJoke(id = -1, joke = e.message.toString()))
            println(e.message)
        }
    }
}