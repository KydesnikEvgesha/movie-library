package ru.evgeniykravchenko.movielibrary.rest.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.evgeniykravchenko.movielibrary.domain.MovieType
import java.io.Serializable
import java.time.LocalDate

/**
 * DTO для сущности [Movie]
 *
 * @property name наименование киноленты
 * @property type тип киноленты [MovieType.description]
 * @property description описание
 * @property releaseDate дата выхода
 */
@Schema(description = "Модель для отражения сущности киноленты")
data class MovieDto(
    @field:Schema(
        description = "Название киноленты",
        example = "Голодные игры",
        type = "string"
    )
    val name: String = "",

    @field:Schema(
        description = "Тип киноленты",
        example = "Полнометражка (в случае добавления 'FULL')",
        type = "string"
    )
    val type: String = "",

    @field:Schema(
        description = "Описание киноленты",
        example = "Фильм о том как......",
        type = "string"
    )
    val description: String = "",

    @field:Schema(
        description = "Дата выхода",
        example = "01.01.2022",
        type = "date"
    )
    val releaseDate: LocalDate = LocalDate.now()
) : Serializable