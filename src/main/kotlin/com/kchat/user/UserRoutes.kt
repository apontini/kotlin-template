package com.kchat.user

import com.kchat.user.controller.GetUser
import com.kchat.user.infrastructure.UserRepository
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
    route("/v1") {
        route("/users/{id}") {
            val getUserController by inject<GetUser>()
            get { getUserController(this) }
        }
    }
}

fun Application.userRoutes() {
    routing {
        userRoutes()
    }
}