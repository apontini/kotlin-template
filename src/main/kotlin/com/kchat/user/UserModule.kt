package com.kchat.user

import com.kchat.user.application.UserService
import com.kchat.user.web.CreateUser
import com.kchat.user.web.GetUser
import com.kchat.user.infrastructure.UserMongoRepository
import com.kchat.user.infrastructure.UserRepository
import org.koin.dsl.module
import org.litote.kmongo.KMongo

val userModule = module {
    single {
        KMongo.createClient("mongodb://" + System.getenv("DB_USERNAME") + ":" + System.getenv("DB_PASSWORD") + "@mongodb:27017")
    }
    single { UserMongoRepository() as UserRepository }
    single { UserService() }

    single { GetUser() }
    single { CreateUser() }
}
