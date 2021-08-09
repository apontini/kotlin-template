package com.kchat.user.web

import com.kchat.user.application.UserService
import com.kchat.user.exceptions.UserAlreadyExistsException
import com.kchat.user.web.dto.CreateUserRequest
import com.kchat.webapp.Controller
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.pipeline.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CreateUser : Controller(), KoinComponent {
    private val userService by inject<UserService>();

    override suspend fun PipelineContext<Unit, ApplicationCall>.call() {
        val request = call.receive<CreateUserRequest>()
        try {
            call.respond(
                HttpStatusCode.Companion.Created,
                userService.createUser(request.email, request.name)
            )
        } catch (e: UserAlreadyExistsException) {
            call.respondText(status = HttpStatusCode.Conflict) { "User already exists" }
        }
    }

}