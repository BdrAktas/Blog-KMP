package org.aktasbedir.blogmultiplatform.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
actual data class User(
    @SerialName(value = "_id")
    //ObjectIdGenerator.newObjectId<String>().id.toHexString() ile default unique value uremiyoruz
    // cunku KMongo bir Java librarysi ve KMP'de JS kismi icin kullanamiyoruz.
    actual val _id: String = "",
    actual val username: String = "",
    actual val password: String = ""
)

@Serializable
actual data class UserWithoutPassword(
    @SerialName(value = "_id")
    actual val _id: String = "",
    actual val username: String = ""
)