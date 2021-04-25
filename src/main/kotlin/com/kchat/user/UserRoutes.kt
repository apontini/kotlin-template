package com.kchat.user

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
    route("/users/{id}") {
        val userRepository by inject<UserRepository>()

        get {
            call.respondText(userRepository.findById(call.parameters["id"] ?: "null")?.email ?: "no")
        }
    }
}

fun Application.userRoutes() {
    routing {
        userRoutes()
    }
}