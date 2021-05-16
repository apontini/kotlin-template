package com.kchat.user

import com.kchat.user.controller.GetUser
import com.kchat.user.infrastructure.UserMongoRepository
import com.kchat.user.infrastructure.UserRepository
import org.koin.dsl.module
import org.litote.kmongo.KMongo

val userModule = module {
    single { KMongo.createClient(System.getenv("MONGO_CREDENTIALS")) }
    single { UserMongoRepository() as UserRepository }

    single { GetUser() }
}
