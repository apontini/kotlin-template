package me.apontini.ktemplate.user.web

import me.apontini.ktemplate.user.application.UserService
import me.apontini.ktemplate.webapp.Controller
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.util.pipeline.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetUser : Controller(), KoinComponent {
    private val userService by inject<UserService>();

    override suspend fun PipelineContext<Unit, ApplicationCall>.call() {
        requireNotNull(call.parameters["id"]) { "invalid user Id" }
        val user = userService.getUserById(call.parameters["id"]!!);
        if(user == null) {
            call.respond(HttpStatusCode.NotFound)
        } else {
            call.respond(user)
        }
    }

}