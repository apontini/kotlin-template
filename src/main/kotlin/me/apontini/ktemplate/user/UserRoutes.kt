package me.apontini.ktemplate.user

import me.apontini.ktemplate.user.web.CreateUser
import me.apontini.ktemplate.user.web.GetUser
import io.ktor.application.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
    route("/v1") {
        route("/users/{id}") {
            val getUserController by inject<GetUser>()
            get { getUserController(this) }
        }
        route("/users") {
            val createUserController by inject<CreateUser>()
            post { createUserController(this) }
        }
    }
}

fun Application.userRoutes() {
    routing {
        userRoutes()
    }
}