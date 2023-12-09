package me.apontini.ktemplate.user.web

import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*
import me.apontini.ktemplate.user.application.UserService
import me.apontini.ktemplate.webapp.Controller
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetUser : Controller(), KoinComponent {
    private val userService by inject<UserService>();

    override suspend fun PipelineContext<Unit, ApplicationCall>.call() {
        call.respond(
            call.parameters["id"]?.let { userService.getUserById(it) } ?: HttpStatusCode.NotFound
        )
    }

}