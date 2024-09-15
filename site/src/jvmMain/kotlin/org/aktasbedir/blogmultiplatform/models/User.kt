package org.aktasbedir.blogmultiplatform.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.id.ObjectIdGenerator


@Serializable
actual data class User(
    // burda Serializable Librarysi ile annotation kullanarak "_id"'ye cevirioruz
    // cunku MongoDB'ta "_id" ile tutuluyor
    @SerialName(value = "_id")
    //ObjectIdGenerator.newObjectId<String>().id.toHexString() ile default unique value ureticez
    actual val _id: String = ObjectIdGenerator.newObjectId<String>().id.toHexString(),
    actual val username: String = "",
    actual val password: String = ""
)

@Serializable
actual data class UserWithoutPassword(
    @SerialName(value = "_id")
    actual val _id: String = ObjectIdGenerator.newObjectId<String>().id.toHexString(),
    actual val username: String = ""
)