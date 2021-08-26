package com.kchat

import com.kchat.user.userModule
import com.kchat.user.userRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.netty.*
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.Koin

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {

    install(Koin) {
        modules(userModule)
    }
    install(CallLogging)
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
