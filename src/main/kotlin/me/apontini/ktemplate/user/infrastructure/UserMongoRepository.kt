package me.apontini.ktemplate.user.infrastructure

import me.apontini.ktemplate.user.domain.User
import com.mongodb.client.MongoClient
import com.mongodb.client.model.ReplaceOptions
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.replaceOneById

class UserMongoRepository : UserRepository, KoinComponent {
    private val client by inject<MongoClient>()

    override fun save(user: User): User {
        client.getDatabase(System.getenv("DB_NAME"))
            .getCollection("user", User::class.java)
            .replaceOneById(user.id, user, ReplaceOptions().upsert(true))
        return user
    }

    override fun delete(user: User) {
        client.getDatabase(System.getenv("DB_NAME"))
            .getCollection("user", User::class.java)
            .deleteOne(User::id eq user.id)
    }

    override fun findByEmail(email: String): User? {
        return client.getDatabase(System.getenv("DB_NAME"))
            .getCollection("user", User::class.java)
            .findOne(User::email eq email)
    }

    override fun findById(id: String): User? {
        return client.getDatabase(System.getenv("DB_NAME"))
            .getCollection("user", User::class.java)
            .findOne(User::id eq id)
    }

}