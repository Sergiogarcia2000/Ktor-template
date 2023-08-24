package com.routes.v1

import com.template.database.ExposedUser
import com.template.database.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database

fun Route.users() = route("users") {
    val userService = UserService()

    // Create user
    post("") {
        val user = call.receive<ExposedUser>()
        val id = userService.create(user)
        call.respond(HttpStatusCode.Created, id)
    }

    // Read user
    get("{id}") {
        val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
        val user = userService.read(id)
        if (user != null) {
            call.respond(HttpStatusCode.OK, user)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }

    // Update user
    put("{id}") {
        val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
        val user = call.receive<ExposedUser>()
        userService.update(id, user)
        call.respond(HttpStatusCode.OK)
    }

    // Delete user
    delete("{id}") {
        val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
        userService.delete(id)
        call.respond(HttpStatusCode.OK)
    }
}