package ru.evgeniykravchenko.movielibrary.rest.filter

import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable

/**
 * Фильтр поиска кинолент
 *
 * @property offset кол-во элементов, которое необходимо пропустить
 * @property limit кол-во выбираемых элементов
 * @property movieQuery условия поиска кинолент
 */
@Schema(description = "Фильтр поиска кинолент")
class MovieFilter(
    @field:Schema(
        description = "кол-во элементов, которое необходимо пропустить",
        example = "0",
        type = "int"
    )
    val offset: Int = 0,

    @field:Schema(
        description = "кол-во выбираемых элементов",
        example = "10",
        type = "int"
    )
    val limit: Int = 10,

    @field:Schema(
        description = "условия поиска кинолент",
        type = "array"
    )
    val movieQuery: List<MovieQuery> = listOf()
) : Serializable

/**
 * Условие поиска кинолент
 *
 * @property fieldName наименование поля, по которому будет происходит поиск
 * @property value значение поля, по которому будет происходит поиск
 */
@Schema(description = "Фильтр поиска кинолент")
class MovieQuery(
    @field:Schema(
        description = "наименование поля, по которому будет происходит поиск",
        example = "name",
        type = "string"
    )
    val fieldName: String = "",

    @field:Schema(
        description = "значение поля, по которому будет происходит поиск",
        example = "Голодные игры",
        type = "string"
    )
    val value: String = ""
) : Serializable
