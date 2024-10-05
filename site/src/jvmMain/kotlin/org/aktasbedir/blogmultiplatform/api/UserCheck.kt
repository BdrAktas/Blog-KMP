package org.aktasbedir.blogmultiplatform.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.aktasbedir.blogmultiplatform.data.MongoDB
import org.aktasbedir.blogmultiplatform.models.User
import org.aktasbedir.blogmultiplatform.models.UserWithoutPassword
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

// burada website/api/usercheck endpointi tanimlandi, artik bu endpoint icin post request kabul edebiliriz
@Api(routeOverride = "usercheck")
suspend fun userCheck(context: ApiContext) {

    try {
        // context.req. aslinda userin bize post gonderdigi sonra bu ByteArray'i alip Stringe ceviriyoruz
        // let ile eger null degilse bu Stringten decode edip bir User object olusturuoruz
        val userRequest =
            context.req.body?.decodeToString()?.let { Json.decodeFromString<User>(it) }
        // burda eger userRequest(yani Client{frontend}'de post edilen) null degil ise
        // bunu MongoDB'da checkUserExistence fonksiyonuna trigger edip karsilastiracagiz
        val user = userRequest?.let {
            context.data.getValue<MongoDB>().checkUserExistence(
                User(
                    // burdaki "it" aslinda user variable'i
                    username = it.username,
                    //  Boyle direk plain[ password = it.password] vermek yerine hashliyip MongoDB'de oyle karsilastiricaz
                    password = hashPassword(it.password)
                )
            )
        }

        // Eger user MongoDb'de exist ise useri UserWithoutPassword ile response donucez
        if (user != null) {
            context.res.setBodyText(
                Json.encodeToString(
                    UserWithoutPassword(
                        _id = user._id,
                        username = user.username
                    )
                )
            )
        } else {
            context.res.setBodyText(Json.encodeToString("User doesn't exist."))
        }
    } catch (e: Exception) {
        context.res.setBodyText(Json.encodeToString(e.message))

    }
}

@Api(routeOverride = "usercheckid")
suspend fun checkUserId(context: ApiContext) {
    try {
        // idRequest ile bize request eden userin id'sini alacagiz
        val idRequest =
            context.req.body?.decodeToString()?.let { Json.decodeFromString<String>(it) }
        val result = idRequest?.let { context.data.getValue<MongoDB>().checkUserId(it) }

        //usera gosterecegin response
        if (result != null) {
            context.res.setBodyText(Json.encodeToString(result))
        } else {
            context.res.setBodyText(Json.encodeToString(false))
        }

    } catch (e: Exception) {
        context.res.setBodyText(Json.encodeToString(false))
    }
}


private fun hashPassword(password: String): String {

    //java.security package'dan SHA-256 algoritmasi kullanacagiz
    val messageDigest = MessageDigest.getInstance("SHA-256")
    val hashBytes = messageDigest.digest(password.toByteArray(StandardCharsets.UTF_8))
    val hexString = StringBuffer()

    for (byte in hashBytes) {
        // "%02x" bu format 2 digit hexadecimal yapmak icin
        hexString.append(String.format("%02x", byte))
    }

    return hexString.toString()
}