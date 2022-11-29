package ru.evgeniykravchenko.movielibrary.rest.dto

import ru.evgeniykravchenko.movielibrary.domain.FilmType
import java.io.Serializable
import java.time.LocalDate

/**
 * A DTO for the {@link ru.evgeniykravchenko.movielibrary.domain.Film} entity
 */
data class FilmDto(
    val name: String = "",
    val type: String = "",
    val description: String = "",
    val releaseDate: LocalDate = LocalDate.now()
) : Serializable