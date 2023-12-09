package me.apontini.ktemplate

import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import me.apontini.ktemplate.user.userModule
import me.apontini.ktemplate.user.userRoutes
import org.koin.ktor.plugin.Koin
import org.slf4j.event.*

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {

    install(Koin) {
        modules(userModule)
    }
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
    install(ContentNegotiation) {
        json(Json { prettyPrint = true })
    }

    userRoutes()
    routing {
        get("/") {
            call.respondBytes(contentType = ContentType.Text.Html) {
                "<h1>I'm working! \uD83D\uDC0B</h1>".toByteArray(Charsets.UTF_16)
            }
        }
    }
}
