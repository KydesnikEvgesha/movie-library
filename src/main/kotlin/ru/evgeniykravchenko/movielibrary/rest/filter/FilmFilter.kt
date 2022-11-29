package ru.evgeniykravchenko.movielibrary.rest.filter

import java.io.Serializable

class FilmFilter(
    val offset: Int = 0,
    val limit: Int = 10,
    val filmQuery: List<FilmQuery> = listOf()
) : Serializable

class FilmQuery(
    val fieldName: String = "",
    val value: String = ""
) : Serializable
