package ru.evgeniykravchenko.movielibrary.configuration

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import org.springframework.boot.SpringBootConfiguration

@SpringBootConfiguration
@OpenAPIDefinition(
    info = Info(
        title = "Movie Library API",
        version = "0.1",
        license = License(name = "Apache 2.0"),
        description = "Библиотека кинолент",
        contact = Contact(name = "Evgeniy Kravchenko", email = "fizikaru61@gmail.com")
    )
)
class OpenApiConfiguration