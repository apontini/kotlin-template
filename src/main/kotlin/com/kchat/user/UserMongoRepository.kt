package com.kchat.user

import com.mongodb.client.MongoClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.litote.kmongo.eq
import org.litote.kmongo.findOne

class UserMongoRepository : UserRepository, KoinComponent {
    private val client by inject<MongoClient>()

    override fun save(user: User): User {
        client.getDatabase("kchat")
            .getCollection("user", User::class.java)
            .insertOne(user)
        return user
    }

    override fun delete(user: User) {
        client.getDatabase("kchat")
            .getCollection("user", User::class.java)
            .deleteOne(User::id eq user.id)
    }

    override fun findByEmail(email: String): User? {
        return client.getDatabase("kchat")
            .getCollection("user", User::class.java)
            .findOne(User::email eq email)
    }

    override fun findById(id: String): User? {
        return client.getDatabase("kchat")
            .getCollection("user", User::class.java)
            .findOne(User::id eq id)
    }

    override fun update(newUser: User): User {
        client.getDatabase("kchat")
            .getCollection("user", User::class.java)
            .replaceOne(User::id eq newUser.id, newUser)
        return newUser
    }

}