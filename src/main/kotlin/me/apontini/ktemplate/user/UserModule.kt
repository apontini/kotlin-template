package me.apontini.ktemplate.user

import me.apontini.ktemplate.user.application.UserService
import me.apontini.ktemplate.user.web.CreateUser
import me.apontini.ktemplate.user.web.GetUser
import me.apontini.ktemplate.user.infrastructure.UserMongoRepository
import me.apontini.ktemplate.user.infrastructure.UserRepository
import me.apontini.ktemplate.user.web.DeleteUser
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
    single { DeleteUser() }
}
