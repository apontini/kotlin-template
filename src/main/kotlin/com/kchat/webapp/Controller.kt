package com.kchat.webapp

import io.ktor.application.*
import io.ktor.util.pipeline.*

abstract class Controller {
    suspend operator fun invoke(context: PipelineContext<Unit, ApplicationCall>) {
        //should do common stuff with context
        context.call()
    }

    abstract suspend fun PipelineContext<Unit, ApplicationCall>.call();
}