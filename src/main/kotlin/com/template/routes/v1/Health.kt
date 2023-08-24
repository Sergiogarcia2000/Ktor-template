package com.routes.v1

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.health() = route("health") {
    get {
        call.respond(HttpStatusCode.OK, "OK")
    }
}