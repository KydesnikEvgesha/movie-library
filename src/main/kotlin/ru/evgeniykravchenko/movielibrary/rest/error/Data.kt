package ru.evgeniykravchenko.movielibrary.rest.error

data class Data(
    val message: String,
    val field: String,
    val invalidValue: Any
)
