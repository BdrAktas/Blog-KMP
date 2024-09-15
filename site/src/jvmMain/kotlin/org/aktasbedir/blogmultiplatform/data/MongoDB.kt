package org.aktasbedir.blogmultiplatform.data

import com.varabyte.kobweb.api.data.add
import com.varabyte.kobweb.api.init.InitApi
import com.varabyte.kobweb.api.init.InitApiContext
import kotlinx.coroutines.reactive.awaitFirst
import org.aktasbedir.blogmultiplatform.models.User
import org.aktasbedir.blogmultiplatform.util.Constants.DATABASE_NAME
import org.litote.kmongo.and
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.reactivestreams.getCollection


@InitApi
fun initMongoDB(context: InitApiContext) {
    System.setProperty(
        "org.litote.mongo.test.mapping.service",
        "org.litote.kmongo.serialization.SerializationClassMappingTypeService"
    )
    // Bunsuz MongoDB'i kullanamiyoruz
    context.data.add(MongoDB(context))
    //  initte tanimladik boylece KobwebApi'dan MongoDB'i kullanabiliyoruz
}

class MongoDB(private val context: InitApiContext) : MongoRepository {
    private val client = KMongo.createClient()
    private val database = client.getDatabase(DATABASE_NAME)
    private val collection = database.getCollection<User>()

    //bu fonksiyon user icin MongoDB'e bakicak ve karsilastiracak
    override suspend fun checkUserExistence(user: User): User? {
        return try {
            collection
                .find(
                    and(
                        User::username eq user.username,
                        User::password eq user.password
                    )
                ).awaitFirst()
        } catch (e: Exception) {
            context.logger.error(e.toString())
            null
        }
    }
}


