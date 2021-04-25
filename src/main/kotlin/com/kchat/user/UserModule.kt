package com.kchat.user

import org.koin.dsl.module
import org.litote.kmongo.KMongo

val userModule = module {
    single { UserMongoRepository() as UserRepository }
    single { KMongo.createClient(System.getenv("MONGO_CREDENTIALS")) }
}
