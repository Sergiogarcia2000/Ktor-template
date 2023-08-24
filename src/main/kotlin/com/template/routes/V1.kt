package com.template.routes

import com.routes.v1.health
import com.routes.v1.users
import io.ktor.server.routing.*

fun Routing.V1() {

    route("/v1") {
        health()
        users()
    }
}