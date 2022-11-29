package ru.evgeniykravchenko.movielibrary.rest.error

data class ErrorMessage(
    val timestamp: String,
    val status: Int,
    val error: String,
    val message: String,
    val path: String,
    val data: List<Data>?
)
