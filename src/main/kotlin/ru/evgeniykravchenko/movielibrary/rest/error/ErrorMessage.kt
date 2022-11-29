package ru.evgeniykravchenko.movielibrary.rest.error

import org.springframework.http.ResponseEntity

/**
 * Сообщение ошибки. Возвращается в [ResponseEntity.body]
 *
 * @property timestamp дата и время
 * @property status HTTP статус
 * @property error вид ошибки
 * @property message сообщение ошибки
 * @property path URL путь
 */
data class ErrorMessage(
    val timestamp: String,
    val status: Int,
    val error: String,
    val message: String,
    val path: String,
)
