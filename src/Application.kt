package com.example

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module() {
    val server = embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            register(
                ContentType.Application.Json,
                JacksonConverter()
            )
        }

        routing {
            // No exception: http://localhost:8080/4083
            // Exception: http://localhost:8080/4084
            get("/{number}") {
                val numberOfChars = call.parameters["number"]?.toInt()!!
                val value = (0..numberOfChars).joinToString("") { "1" }
                val data = listOf(value)

                call.respond(HttpStatusCode.OK, data)
            }
        }
    }

    server.start(wait = true)
}
