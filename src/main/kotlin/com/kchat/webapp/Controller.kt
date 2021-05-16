package com.kchat.webapp

import io.ktor.application.*
import io.ktor.util.pipeline.*

abstract class Controller {
    operator fun invoke(context: PipelineContext<Unit, ApplicationCall>) {
        //should do common stuff with context
        call(context)
    }

    abstract fun call(context: PipelineContext<Unit, ApplicationCall>);
}