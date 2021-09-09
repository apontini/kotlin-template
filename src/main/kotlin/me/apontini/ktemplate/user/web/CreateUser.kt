package me.apontini.ktemplate.user.web

import me.apontini.ktemplate.user.application.UserService
import me.apontini.ktemplate.user.exceptions.UserAlreadyExistsException
import me.apontini.ktemplate.user.web.dto.CreateUserRequest
import me.apontini.ktemplate.webapp.Controller
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