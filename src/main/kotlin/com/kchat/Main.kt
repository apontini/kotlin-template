package com.kchat

import com.kchat.user.userModule
import com.kchat.user.userRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.server.netty.*
import org.koin.ktor.ext.Koin

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {

    install(Koin) {
        modules(userModule)
    }
    install(CallLogging)

    userRoutes()
}
