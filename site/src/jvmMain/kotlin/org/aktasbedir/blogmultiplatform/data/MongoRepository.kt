package org.aktasbedir.blogmultiplatform.data

import org.aktasbedir.blogmultiplatform.models.User

//Mongo ile interact edicek tum metodlar burada tanimlanicak
interface MongoRepository {
    // bu fonksiyon return null ise user does not exist, varsa return user
    suspend fun checkUserExistence(user: User): User?
}