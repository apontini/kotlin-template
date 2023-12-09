package me.apontini.ktemplate.user

import me.apontini.ktemplate.user.web.CreateUser
import me.apontini.ktemplate.user.web.GetUser
import io.ktor.server.application.*
import io.ktor.server.routing.*
import me.apontini.ktemplate.user.web.DeleteUser
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
    route("/v1") {
        route("/users/{id}") {
            val getUserController by inject<GetUser>()
            val deleteUserController by inject<DeleteUser>()
            get { getUserController(this) }
            delete { deleteUserController(this) }
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